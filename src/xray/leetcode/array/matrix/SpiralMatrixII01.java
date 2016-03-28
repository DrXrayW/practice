package xray.leetcode.array.matrix;

public class SpiralMatrixII01 {
	/*
	 * TIP use two direction vectors for the move control
	 * TIP use row and col, note that right/left is col, up/down is row
	 */
    public int[][] generateMatrix(int n) {
        if(n<0){
            return null;
        }
        int[][] res = new int[n][n];
        
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int k = 1;
        /**
         * TIP like the Spiral Matrix, we turn at the last one! so we use all < here:
         */
        while( (left<right)&&(top<bottom) ) { 
            for(int j=left;j<right;j++){
                res[top][j] = k;
                k++;
            }
            for(int i=top;i<bottom;i++){
                res[i][right] = k;
                k++;
            }
            for(int j=right;j>left;j--){
                res[bottom][j] = k;
                k++;
            }
            for(int i=bottom;i>top;i--){
                res[i][left] = k;
                k++;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        
        if(n%2!=0){  //because we turned before the last one, when n is odd, we need to output the last one
            res[n/2][n/2] = k;
        }
        
        return res;
    }
}
