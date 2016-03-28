package xray.leetcode.array.matrix;

/*
 * A worse solution, use two arrays to track the 0s, using O(m+n) spaces.

IN SHORT:


Trick, use first row and first col to track 0's, 
however, we need to track whether there was one there before our mark, 
so that we can know whether or not setting the first row or col. 


 * IDEA: use the first row and first col to mark whether there is a zero in that row/col
 * Note that when we mark, it is fine: for the unit in the first row first col will be set to 0 anyways
 * the only thing we miss is that we cannot tell whether the zero was there (which requires to set other units zero in the first row/col)
 * or it was set by some other element.  So we scan the first row and first col, and note it down whether it was one there, and set all to zero as the last step if there was one.
 * 
 * TIP since the first row and col has been taken care of, we can loop from index 1
 * 
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if(matrix==null){
            return;
        }
        int rowCount = matrix.length;
        if(rowCount==0){
            return;
        }
        int colCount = matrix[0].length;
        if(colCount==0){
            return;
        }
        
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        for(int col=0;col<colCount;col++){
            if(matrix[0][col] == 0){
                firstRowZero = true;
            }
        }
        
        for(int row=0;row<rowCount;row++){
            if(matrix[row][0] == 0){
                firstColZero = true;
            }
        }
        
        for(int row=1;row<rowCount;row++){
            for(int col=1;col<colCount;col++){
                if(matrix[row][col]==0){
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        
        for(int row=1;row<rowCount;row++){
            for(int col=1;col<colCount;col++){
                if( matrix[row][0]==0  || matrix[0][col] == 0){
                    matrix[row][col]=0;
                }
            }
        }

        if(firstRowZero){
            for(int col=0;col<colCount;col++){
                matrix[0][col] = 0;
            }
        }
        
        if(firstColZero){
            for(int row=0;row<rowCount;row++){
                matrix[row][0] = 0;
            }
        }
    }
}
