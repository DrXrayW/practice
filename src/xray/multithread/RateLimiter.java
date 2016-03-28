package xray.multithread;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 
 * token bucket
 * http://en.wikipedia.org/wiki/Token_bucket
 * 
 *  
 *  IDEA:
 *  
 *  1. maintain a long interval (default 100ms) and a batchSize default to 0
 *  2. for each rps, calculate the batchSize = rps / interal
 *  	(optimization) if batchSize is too small (<0.1), then batchSize and interval *10
 *  3. schedule the addPermit task at interval, each time add batchSize (note that batchSize can be 1.2, etc. )
 *  4. use lock for all operations: doSetPermit, addPermit |  request, reset are optional
 *  5. on request, we must see whether there is any permit >=1d, then permit--
 *  6. on addPermit, we will not add to permit when permit is over the cap: (for non 0 rps), when rps >= 1, then the cap is rps, when rps <1, the cap is 1
 *  7. start with rps = 0 no schedule, when rps is set to 0, stop scheduler and reset values
 *  8. use  ScheduledExecutorService permitGen = Executors.newScheduledThreadPool(1)
 *  	ScheduledExecutorService taskRunner = Executors.newScheduledThreadPool(3);
 * permitGen.scheduleAtFixedRate( new TimerTask(){
				@Override
				public void run(){
					addPermit();
				}
			}, 0L, interval, TimeUnit.MILLISECONDS);
			
	taskRunner.execute(task); 
 */

/*
 * if we are using the 1/r then we need to 
 *  deal with 0 interval
 * 
 * there are a few cases:
 * 1. when rps > 10, 
 * lets assume if rps > 1000, then it is the multiply of 1000 or we will ignore the difference
 * actually, we can let the permit to be double, so that we can have 0.6 + 0.6 = 1.2 so that we won't be that much off
 * 
 * so we are to get rps / 1000, for each milsec. we may consider using 100 ms so that it would be rps / 10 for permit batch size
 * interval is 100ms.
 * 
 * 2. when rps < 10, the above method would still work
 * 
 * 3. when rps < 1, say 0.01 that means 100 sec per request. 
 * 
 * while the permit way would still work, as it will eventually adds up.
 * 
 *  there is no expire windows or something like that. 
 *  
 *  rps / 10 < 0.1
 *  
 *  we may adjust rps so that the added permit is >=0.1, by multiplying the interval 10s. 
 * 
 * 
 */

public class RateLimiter {
	private static double DEFAULT_RPS = 0d;
	private static long DEFAULT_INTERVAL = 100;
	private static double DEFAULT_BATCH_SIZE = 0d;
	
	private double rps = DEFAULT_RPS; //default value
	private long interval = DEFAULT_INTERVAL; //100ms default interval
	private double batchSize = DEFAULT_BATCH_SIZE;
	
	private double permits = 0d;
	
	ScheduledExecutorService permitGen = Executors.newScheduledThreadPool(1);	
	ScheduledExecutorService taskRunner = Executors.newScheduledThreadPool(3);
	
	final Object lock = new Object();
	final Runnable task;
	
	public RateLimiter(Runnable task){
		this.task = task;
	}
	public RateLimiter(Runnable task, double rps){
		this.task = task;
		setRPS(rps);
	}
	
	public void setRPS(double rps){
		if(rps < 0d){
			throw new IllegalArgumentException("rps must be positive.");
		}
		doSetRPS(rps);
	}
	
	private void reset(){
		synchronized(lock){
			if(!permitGen.isShutdown()){
				permitGen.shutdownNow();
			}
			rps = DEFAULT_RPS;
			interval = DEFAULT_INTERVAL;
			batchSize = DEFAULT_BATCH_SIZE;
		}
	}
	
	private void doSetRPS(double rps){
		synchronized(lock){
			//changes nothing
			if(rps==this.rps){
				return; //nothing changed
			}
			
			//from non 0 to 0 reset
			if(rps==0d){
				reset();
				return;
			}
			
			//update numbers
			this.rps = rps;
			batchSize = this.rps / (double)interval;
			while(batchSize<0.1d){
				batchSize*=10;
				interval*=10;
			}
			permitGen.scheduleAtFixedRate( new TimerTask(){
				@Override
				public void run(){
					addPermit();
				}
			}, 0L, interval, TimeUnit.MILLISECONDS);
		}
	}
	
	private void addPermit(){
		synchronized(lock){ 
			if(permits < Math.max(1d, rps)){ //when rps is > 1, then the cap is rps, if rps is < 1, then it is 1 (e.g. for 10 sec case)
				permits+=batchSize;
			}
		}
	}
	
	public boolean request(){
		synchronized(lock){ 
			if(permits>=1d){
				permits--;
				taskRunner.execute(task);
				return true;
			}else{
				return false;
			}
		}
	}
}
