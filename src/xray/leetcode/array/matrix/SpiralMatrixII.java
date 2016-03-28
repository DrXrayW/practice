package xray.leetcode.array.matrix;

public class SpiralMatrixII {
	/*
	 * TIP use two direction vectors for the move control
	 * TIP use row and col, note that right/left is col, up/down is row
	 */
    public int[][] generateMatrix(int n) {
        if(n<0){
            return null;
        }
        int[][] res = new int[n][n];
        
        int rowCount = n;
        int colCount = n;
        
        int row=0;
        int col=-1;
        int k = 1;
        /**
         * TIP like the Spiral Matrix, we turn at the last one! so we use all < here:
         */
        while( true ) {
        	for(int i=0;i<colCount;i++){
        		col++;
        		res[row][col] = k;
        		k++;
        	}
        	rowCount--;
        	if(rowCount==0){
        		break;
        	}
        	for(int i=0;i<rowCount;i++){
        		row++;
        		res[row][col] = k;
        		k++;
        	}
        	colCount--;
        	if(colCount==0){
        		break;
        	}

        	for(int i=0;i<colCount;i++){
        		col--;
        		res[row][col] = k;
        		k++;
        	}
        	rowCount--;
        	if(rowCount==0){
        		break;
        	}
        	for(int i=0;i<rowCount;i++){
        		row--;
        		res[row][col] = k;
        		k++;
        	}
        	colCount--;
        	if(colCount==0){
        		break;
        	}
        }
        
        return res;
    }
}
