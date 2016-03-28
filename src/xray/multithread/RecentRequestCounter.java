package xray.multithread;
/*
 * maintain a circular array
 * by head and tail index
 * 
 * either getCount or add new
 * always update
 * 
 * while update: get the number of shifts needed, remove head count from total, clear head, increment head and tail in circular way. 
 * 
 * on start up, or the oldestTime is too old (< now - range), then clear everything to 0, set oldest time to now - range
 * 
 * cannot save the time on update as we need to deduct and clear the 0s. 
 * 
 * always add count to tail, add count to total. 
 * 
 * 
 */
public class RecentRequestCounter {
	private static long DEFAULT_UNIT = 100;
	private static long DEFAULT_RANGE = 60000;
	private long unit = DEFAULT_UNIT;
	private long range = DEFAULT_RANGE;
	private long[] buckets;
	private long totalCount = 0;
	private Object lock = new Object();
	private long oldestTime;
	private int head, tail;
	private int size;
	
	public RecentRequestCounter(){
		size = (int)(range / unit);
		buckets = new long[size];
		head = 0;
		tail = size - 1;
		long now = System.currentTimeMillis();
		oldestTime  = now;
	}
	
	public long getCount(){
		synchronized(lock){
			long now = System.currentTimeMillis();
			update(now);
			return totalCount;
		}
	}
	
	public void add(){
		synchronized(lock){
			long now = System.currentTimeMillis();
			update(now);
			add(now);
		}
	}
	
	private void add(long now){
		buckets[tail] ++;
		totalCount++;
	}
	
	private void update(long now){
		long diff = Math.max(0,  now - oldestTime);
		int shift = (int)(diff / unit);
		shift = Math.min(size, shift); //maximum size times
		for(int i=0;i<shift;i++){
			totalCount-= buckets[head];
			buckets[head] = 0;
			head = increment(head);
			tail = increment(tail);
			oldestTime+=unit;
		}
		if(shift>=size){
			oldestTime = now - range;
		}
	}

	private int increment(int index) {
		return (index+1)%size;
	}
}
