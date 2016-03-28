package xray.leetcode.enumeration;

import java.util.*;

public class NQueens {
    public List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<String[]>();
        if(n<=0){
            return res;
        }
        
        int[] posAtRow = new int[n];
        int row=0;
        return solveNQueens(n, row, posAtRow, res);
    }
    
    private List<String[]> solveNQueens(int n, int row, int[] posAtRow, List<String[]> res){
        for(int i=0;i<n;i++){
            posAtRow[row] = i;
            if(pass(posAtRow, row)){
                if(row == (n-1) ){
                    res.add(getSolution(posAtRow));   
                }else{
                    solveNQueens(n, row+1, posAtRow, res);        
                }
            }else{
                //stop and return
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
    
    static private String[] getSolution(int[] posAtRow){
        int n=posAtRow.length;
        String[] sol = new String[n];
        for(int row=0;row<n;row++){
            StringBuilder b = new StringBuilder();
            for(int col=0;col<n;col++){
                if(posAtRow[row]==col){
                    b.append("Q");
                }else{
                    b.append(".");
                }
            }
            sol[row] = b.toString();
        }
        return sol;
    }
}
