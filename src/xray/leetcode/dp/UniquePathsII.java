package xray.leetcode.dp;

/*
 * 
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null){
            return 0;
        }
        int m = obstacleGrid.length;
        if(m==0){
            return 0;
        }
        int n = obstacleGrid[0].length;
        if(n==0){
            return 0;
        }
        int[][] dp = new int[m][n]; //number of ways to reach [i][j]
        
        dp[0][0] = obstacleGrid[0][0]==1 ? 0 : 1; //TIP: this is the real initialization
        										  //TIP: Unless we know for sure what it is, better do dp on the firsts too.

        for(int i=1;i<m;i++){
            dp[i][0] = obstacleGrid[i][0]==1 ? 0 : dp[i-1][0]; //TIP: this is the real initialization
        }
        
        for(int j=1;j<n;j++){
            dp[0][j] = obstacleGrid[0][j]==1 ? 0 : dp[0][j-1]; //TIP: this is the real initialization
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = obstacleGrid[i][j]==1 ? 0 : dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}	
