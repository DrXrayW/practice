package xray.leetcode.interview;

public class Box {
	public int height;
	public int depth;
	public int width;
	public int area;
	public Box(int depth, int width, int height){
		this.depth = depth;
		this.width = width;
		this.height = height;
		area = width * depth;
		if(depth>width){ //depth always smaller
			this.depth = width;
			this.width = depth;
		}
	}
	
	@Override
	public String toString(){
		return "(D:" + depth +" ,W:" + width + ", H:" + height +")" ; 
	}
}
