package xray.leetcode.dp;

/*
 * Two dimension DP
 * 
 * Here the right bottom one is our destine, so dp matrix is the same as grid. 
 * 
 * TIP the first one and the last one must be in the sum, so is the current one
 * 
 */
public class MinimumPathSum {
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
        int[][] dp = new int[rowCount][colCount];  
        dp[0][0] = grid[0][0];
        for(int i=1;i<rowCount;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j=1;j<colCount;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for(int i=1;i<rowCount;i++){
            for(int j=1;j<colCount;j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[rowCount-1][colCount-1];
    }
}
