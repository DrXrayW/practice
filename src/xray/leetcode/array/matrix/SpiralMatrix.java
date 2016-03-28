package xray.leetcode.array.matrix;

import java.util.ArrayList;

public class SpiralMatrix {
	/*
	 * TIP use two direction vectors for the move control
	 * TIP use row and col, note that right/left is col, up/down is row
	 */
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
	    if(matrix == null || matrix.length == 0){
	        return result;
	    }
	    int rowcount = matrix.length;
	    int colcount = matrix[0].length;
	    
	    int[] colStep = {1, 0, -1, 0};
	    int[] rowStep = {0, 1, 0, -1};
	    
	    //TIP: Note the visited row and coloum count
	    int visitedRow = 0; 
	    int visitedCol = 0;
	    
	    int row=0; 
	    int col=0;
	    
	    int direction = 0; //0 right, 1 down, 2 left, 3 up
	    while(true){
	        boolean colMove = (direction%2==0);
	        int maxStep = colMove ? colcount - visitedCol : rowcount - visitedRow;
	        if(maxStep==0){ //TIP: stop when maxStep is out
	            break;
	        }
	        for(int i=0;i<maxStep;i++){
	            result.add(matrix[row][col]); //TIP: always add the current one
	            if(i==maxStep-1){  //TIP: most importantly, at the last one, change direction
	                direction = (direction + 1) % 4;
	            }
    	        row+=rowStep[direction]; //TIP: but always move forward
    	        col+=colStep[direction];
	        }
	        if(colMove){
	            visitedRow ++;
	        }else{
	            visitedCol ++;
	        }
	    }
	    return result;
	}
}
