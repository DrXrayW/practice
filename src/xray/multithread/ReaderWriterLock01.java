package xray.multithread;

public class ReaderWriterLock01 {
	private Object lock = new Object();
	private int reader=0;
	private boolean writer = false;
	private int writeRequest = 0;
	
	public void readLock() throws InterruptedException{
		synchronized(lock){
			while(writer||writeRequest>0){
				lock.wait();
			}
			reader ++;
		}
	}
	
	public void readUnlock(){
		synchronized(lock){
			reader--;
			lock.notify();
		}
	}
	
	public void writeLock() throws InterruptedException{
		synchronized(lock){
			writeRequest++;
			while(writer||reader>0){
				lock.wait();
			}
			writeRequest--;
			writer = true;
		}
	}
	
	public void writeUnlock(){
		synchronized(lock){
			writer = false;
			lock.notify();
		}
	}
}
