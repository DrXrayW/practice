package xray.multithread;

public class ReaderWriterLock {
	private Object lock = new Object();
	private int reader=0;
	private boolean writer = false;
	public void readLock() throws InterruptedException{
		synchronized(lock){
			while(writer){
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
			while(writer||reader>0){
				lock.wait();
			}
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
