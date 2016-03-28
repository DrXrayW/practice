package xray.leetcode.geometry;

/*
 * (y - y1) (x2-x1) = (x - x1) (y2-y1)
 * 
 * so 
 * 
 * [after handling special case]
 * 
 * y = (x - x1) (y2- y1) / (x2 - x1) + y1
 *  
 */
public class Line {
	public static void main(String[] args) {
		int x0=0, y0=0;
		int x1=7, y1=5;
		int[][] screen = new int[20][20];
		for(int x=0;x<20;x++){
			int y=getY(x0,y0,x1,y1,x);
			screen[x][y] = 1;
		}
		System.out.println("=====");
		for(int y=19;y>=0;y--){
			for(int x=0;x<20;x++){
				if(screen[x][y]==1){
					System.out.print("X");
				}else{
					System.out.print("O");
				}
			}
			System.out.println();
		}
	}

	private static int getY(int x0, int y0, int x1, int y1, int x) {
		assert(x1!=x0);
		return (int)Math.round(((double)x - (double)x0) * ((double)y1 - (double)y0) / ((double)x1 - (double)x0) + (double)y0) ;
	}
}
