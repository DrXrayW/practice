package xray.leetcode.interview;

import java.util.*;

public class Waterflow {
	public static void main(String[] args) {
		/*
		int[][] h = new int[][]{
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
		};
		*/
		int[][] h = new int[][]{
				{9,9,9,9,9,9},
				{1,2,3,4,5,8},
				{9,2,8,9,5,9},
				{9,2,3,4,5,2},
				{2,2,3,4,5,9},
				{1,9,9,9,4,3},
		};

		Set<Pos> set= getFlowToBothOceans(h);
		
		int[][] output = new int[h.length][h[0].length];
		for(Pos p : set){
			output[p.row][p.col] = 1;
		}
		print(output);
		
	}
	
	public static Set<Pos>  getFlowToBothOceans(int[][] h){
		int rowCount = h.length;
		int colCount = h[0].length;

		int[][] mark = new int[rowCount][colCount];
		
		Queue<Pos> todo = new LinkedList<>();
		for(int row=0;row<rowCount;row++){
			todo.add(new Pos(row, 0));
		}
		markReachable(todo, mark, h, rowCount, colCount);
		
		todo.clear();
		for(int row=0;row<rowCount;row++){
			todo.add(new Pos(row, colCount-1));
		}
		Set<Pos> set = markReachable(todo, mark, h, rowCount, colCount);
		return set;
	}
	
	private static Set<Pos> markReachable(Queue<Pos> todo, int[][] mark, 
			int[][] h, int rowCount, int colCount) {
		Set<Pos> res = new HashSet<Pos>();
		Set<Pos> visited = new HashSet<Pos>();
		while(!todo.isEmpty()){
			Pos p = todo.poll();
			if(visited.contains(p)){ //visited must be at the beginning, as expansion may go to the same node
				continue;
			}
			
			mark[p.row][p.col]++;
			if(mark[p.row][p.col]==2){
				res.add(p);
			}
			visited.add(p);
			
			Set<Pos> expand = new HashSet<Pos>();
			expand.add(new Pos(p.row-1, p.col));
			expand.add(new Pos(p.row+1, p.col));
			expand.add(new Pos(p.row, p.col-1));
			expand.add(new Pos(p.row, p.col+1));
			
			for(Pos next : expand){
				if( 
					inRange(next, rowCount, colCount) &&	
					h[p.row][p.col]<= h[next.row][next.col] ) {
					todo.add(next);
				}
			}
		}
		print(mark);
		return res;
	}

	private static boolean inRange(Pos p, int rowCount, int colCount) {
		return (p.row>=0)&&(p.row<rowCount)&&(p.col>=0)&&(p.col<colCount);
	}

	private static void print(int[][] a){
		System.out.println("===");
		for(int i=0;i<a.length;i++){
			System.out.println(Arrays.toString(a[i]));
		}
	}
}
