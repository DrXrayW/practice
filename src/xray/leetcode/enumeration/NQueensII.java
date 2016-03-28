package xray.leetcode.enumeration;

/*
 * same as NQueens, not returning solution though. this one is simpler
 */
public class NQueensII {
    public int totalNQueens(int n) {
        if(n<=0){
            return 0;
        }
        
        int[] posAtRow = new int[n];
        int row=0;
        return solveNQueens(n, row, posAtRow, 0);
    }
    
    private int solveNQueens(int n, int row, int[] posAtRow, int count){
        int res = count;
        for(int i=0;i<n;i++){
            posAtRow[row] = i;
            if(pass(posAtRow, row)){
                if(row == (n-1) ){
                    res++;   
                }else{
                    res =solveNQueens(n, row+1, posAtRow, res);        
                }
            }else{
                //stop and try next one
                continue;
            }
        }
        return res;
    }
    
    static private boolean pass(int[] posAtRow, int row){
        for(int row2=0;row2<row;row2++){
            int col = posAtRow[row];
            int col2 = posAtRow[row2];
            if(col == col2){
                return false;
            }
            if( (row - row2) == Math.abs(col - col2)){
                return false;
            }
        }
        return true;
    }
}
