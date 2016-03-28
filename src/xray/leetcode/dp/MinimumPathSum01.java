package xray.leetcode.dp;

/*
 * Two dimension DP
 * 
 * Here the right bottom one is our destine, so dp matrix is the same as grid. 
 * 
 * TIP the first one and the last one must be in the sum, so is the current one
 * 
 */
public class MinimumPathSum01 {
    public int minPathSum(int[][] grid) {
        int min = 0;
        if(grid==null){
            return 0;
        }
        int rowCount = grid.length;
        if(rowCount==0){
            return min;
        }
        int colCount = grid[0].length;
        if(colCount==0){
            return min;
        }
        //init
        /*  
        dp[0][0] = grid[0][0];
        for(int i=1;i<rowCount;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j=1;j<colCount;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        */
        int[] pre = new int[colCount];
        for(int i=0;i<rowCount;i++){
        	int[] cur = new int[colCount];
            for(int j=0;j<colCount;j++){
            	if(i==0&&j==0){
            		cur[j] = grid[0][0];
            	}else if(i==0){
            		cur[j] = cur[j-1] + grid[0][j];
            	}else if(j==0){
            		cur[j] = pre[j] + grid[i][j]; //j==0
            	}else{
            		cur[j] = grid[i][j] + Math.min(pre[j], cur[j-1]);
            	}
            }
            pre = cur;
        }
        return pre[colCount-1];
    }
}
