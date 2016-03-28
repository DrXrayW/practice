package xray.leetcode.array.skyline;

public class Endpoint implements Comparable<Endpoint>{
	  Skyline skyline;
	  boolean isLeft; //inclusive
	  boolean isRight; //exclusive
	  Endpoint(Skyline skyline, boolean isLeft){
		  this.skyline = skyline;
		  this.isLeft = isLeft;
		  this.isRight = !this.isLeft;
	  }
	  int getPos(){
		  return isLeft ? skyline.left : skyline.right + 1;
	  }
	  
	@Override
	public int compareTo(Endpoint endpoint2) {
    	return  getPos() - endpoint2.getPos();
	}
	  
}
