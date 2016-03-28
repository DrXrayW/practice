package xray.multithread;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/*
 * 
 
 * 
 */

public class RateLimiter01 {
	private static double DEFAULT_RPS = 0d;
	private double timedCount = DEFAULT_RPS; //default value
	Object lock = new Object();
	ScheduledExecutorService taskRunner = Executors.newScheduledThreadPool(3);
	final Runnable task;
	/*
	 * a queue of time of the desired size
	 * for a new request, dequeue head if it's time is earlier than now - time.
	 * finally, if its size is within the limit return yes, else return false
	 * 
	 * 
	 * it is impossible to give the perfect time window as we do not know when it can reach a good one
	 * say 1.5 is easy: 2*
	 * but 1.1233123, is really hard to say, 
	 * in that case, we can ask for input
	 * 
	 */
	static private long TIME_WINDOW = 1000;
	private long timeWindow = TIME_WINDOW;
	private ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
	
	public RateLimiter01(Runnable task, double rps){
		this.task = task;
		if(rps>=1d){
			this.timedCount = rps;
		}
		while(this.timedCount < 1d){
			timeWindow *=10;
			timedCount*=10;
		}
	}
	
	public boolean allowRequest(){
		if(timedCount<=0d){
			return false;
		}
		long now = System.currentTimeMillis();
		synchronized(lock){
			while(!queue.isEmpty() && (queue.peek()< (now - timeWindow))){
				queue.poll();
			}
			if(queue.size()<timedCount){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public boolean request(){
		if(timedCount<=0d){
			return false;
		}
		long now = System.currentTimeMillis();
		synchronized(lock){
			while(!queue.isEmpty() && (queue.peek()< (now - timeWindow))){
				queue.poll();
			}
			if(queue.size()<timedCount){
				queue.add(now);
				taskRunner.execute(task);
				return true;
			}else{
				return false;
			}
		}
	}
}
