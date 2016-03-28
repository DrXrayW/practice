package xray.leetcode.interview;

import java.util.Arrays;

public class QuadTree {
	public static void main(String[] args) {
		int size = 16;
		QuadTree t1 = new QuadTree(0,size,0,size,0);
		
		int offset = 2;
		int size2 = 8;
		QuadTree t2 = new QuadTree(offset, size2 + offset, offset, size2 + offset,1);
		int[][] screen = new int[size][size];
		print(t2, screen);
		t1.fill(screen);

		offset = 6;
		size2 = 8;
		QuadTree t3 = new QuadTree(offset, size2 + offset, offset, size2 + offset,1);
		print(t3, screen);
		t1.fill(screen);
		
		offset = 4;
		size2 = 8;
		QuadTree t4 = new QuadTree(offset, size2 + offset, offset, size2 + offset,1);
		screen = new int[size][size];
		print(t4, screen);
		QuadTree t5 = new QuadTree(0,size,0,size,0);
		t5.fill(screen);
		
		t1.intersect(t5);
		
		screen = new int[size][size];
		print(t1, screen);
		print(screen);
	}
	
	public void intersect(QuadTree t) {
		if(size==1){
			value = Math.min(value, t.value);
		}
		if(t.upLeft==null){
			upLeft = null;
		}else if(upLeft!=null){
			upLeft.intersect(t.upLeft);
		}
		if(t.upRight==null){
			upRight = null;
		}else if(upRight!=null){
			upRight.intersect(t.upRight);
		}
		if(t.bottomLeft==null){
			bottomLeft = null;
		}else if(bottomLeft!=null){
			bottomLeft.intersect(t.bottomLeft);
		}
		if(t.bottomRight==null){
			bottomRight = null;
		}else if(upLeft!=null){
			bottomRight.intersect(t.bottomRight);
		}
	}

	private void fill(int[][] screen) {
		int size = screen.length;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(screen[i][j]==1){
					insert(i,j);
				}
			}
		}
	}

	private void insert(int i, int j) {
		if(size==1){
			value = 1;
			return;
		}
		int half = (xEnd - xStart)/2;
		boolean left = i<(xStart+half);
		boolean up = j<(yStart+half);
		if(up&&left){
			if(upLeft==null){
				upLeft = new QuadTree(xStart, xStart+half, yStart, yStart+half, 0);
			}
			upLeft.insert(i, j);
		}else if(up&&!left){
			if(upRight==null){
				upRight = new QuadTree(xStart+half, xEnd, yStart, yStart+half, 0);
			}
			upRight.insert(i, j);
		}else if(!up&&left){
			if(bottomLeft==null){
				bottomLeft = new QuadTree(xStart, xStart+half, yStart+half, yEnd, 0);
			}
			bottomLeft.insert(i, j);
		}else{
			if(bottomRight==null){
				bottomRight = new QuadTree(xStart+half, xEnd, yStart+half, yEnd, 0);
			}
			bottomRight.insert(i,j);
		}
	}

	int xStart; //inclusive
	int xEnd; //exclusive
	int yStart; //inclusive
	int yEnd;
	int size;
	int value;
	
	QuadTree upLeft;
	QuadTree upRight;
	QuadTree bottomLeft;
	QuadTree bottomRight;
	
	public QuadTree(int xStart, int xEnd, int yStart, int yEnd, int value){
		this.size = xEnd - xStart;
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
		int half = (xEnd - xStart) / 2;
		if(upLeft==null){
			upLeft = new QuadTree(xStart, xStart + half, yStart, yStart+half, 1);
			upRight = new QuadTree(xStart + half, xEnd, yStart, yStart+half, 1);
			bottomLeft = new QuadTree(xStart, xStart + half, yStart+half, yEnd, 1);
			bottomRight = new QuadTree(xStart + half, xEnd, yStart+half, yEnd, 1);
		}
	}
	static void print(int[][] screen){
		System.out.println("===========");
		for(int i=0;i<screen.length;i++){
			System.out.println(Arrays.toString(screen[i]));
		}
	}
	static void print(QuadTree quadTree, int[][] screen){
		if(quadTree.size==1){
			screen[quadTree.xStart][quadTree.yStart] = quadTree.value;
			return;
		}
		if(quadTree.upLeft!=null){
			print(quadTree.upLeft, screen);
		}
		if(quadTree.upRight!=null){
			print(quadTree.upRight, screen);
		}
		if(quadTree.bottomLeft!=null){
			print(quadTree.bottomLeft, screen);
		}
		if(quadTree.bottomRight!=null){
			print(quadTree.bottomRight, screen);
		}
	}
}
