package xray.leetcode.interview;

import java.util.Arrays;

public class QuadTree01 {
 
	public static void main(String[] args) {
		int size = 16;
		QuadTree01 t1 = new QuadTree01(0,size,0,size,0);
		
		int offset = 2;
		int size2 = 8;
		QuadTree01 t2 = new QuadTree01(offset, size2 + offset, offset, size2 + offset,1);
		int[][] screen = new int[size][size];
		print(t2, screen);
		t1.fillWith(screen);
		
		
		offset = 6;
		size2 = 8;
		QuadTree01 t3 = new QuadTree01(offset, size2 + offset, offset, size2 + offset,1);
		print(t3, screen);
		t1.fillWith(screen);


		offset = 4;
		size2 = 8;
		QuadTree01 t4 = new QuadTree01(offset, size2 + offset, offset, size2 + offset,1);
		screen = new int[size][size];
		print(t4, screen);
		QuadTree01 t5 = new QuadTree01(0,size,0,size,0);
		t5.fillWith(screen);

		t1.intersect(t5);
		
		output(t1);
	}
	
	private static void output(QuadTree01 t1) {
		int size = 16;
		int[][] screen = new int[size][size];
		print(t1, screen);
		print(screen);
	}

	public void intersect(QuadTree01 t) {
		if(size==1){
			value = Math.min(value, t.value);
		}
		for(int i=0;i<4;i++){
			if(t.subtrees[i]==null){
				subtrees[i] = null;
			}else if(subtrees[i]!=null){
				subtrees[i].intersect(t.subtrees[i]);
			}
		}
	}

	private void insert(int i, int j) {
		if(size==1){
			value = 1;
			return;
		}
		int x = i< (xStart+half) ? 0: 1;
		int y = j<(yStart+half) ? 0 : 1;
		int index = y*2 + x;
		if(subtrees[index]==null){
			subtrees[index] = new QuadTree01(xStart+x*half, xEnd-(1-x)*half, yStart+y*half, yEnd -(1-y)*half, 0);
		}
		subtrees[index].insert(i, j);
	}
	
	private void fillWith(int[][] screen) {
		int size = screen.length;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(screen[i][j]==1){
					insert(i,j);
				}
			}
		}
	}


	int xStart; //inclusive
	int xEnd; //exclusive
	int yStart; //inclusive
	int yEnd;
	int size;
	int half;
	int value;
	
	QuadTree01[] subtrees = new QuadTree01[4];
	
	public QuadTree01(int xStart, int xEnd, int yStart, int yEnd, int value){
		this.size = xEnd - xStart;
		this.half = size /2;
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.yStart = yStart;
		this.yEnd = yEnd;
		if(value==1){
			fill();
		}
	}
	private void fill() {
		if(size==1){
			this.value = 1;
			return;
		}
		for(int x=0;x<2;x++){
			for(int y=0;y<2;y++){
				subtrees[y*2+x] = new QuadTree01(xStart+x*half, xEnd-(1-x)*half, yStart+y*half, yEnd -(1-y)*half, 1);
			}
		}
	}
	static void print(int[][] screen){
		System.out.println("===========");
		for(int i=0;i<screen.length;i++){
			System.out.println(Arrays.toString(screen[i]));
		}
	}
	static void print(QuadTree01 quadTree, int[][] screen){
		if(quadTree.size==1){
			screen[quadTree.xStart][quadTree.yStart] = quadTree.value;
			return;
		}
		for(QuadTree01 subtree : quadTree.subtrees){
			if(subtree!=null){
				print(subtree, screen);
			}
		}
	}
}
