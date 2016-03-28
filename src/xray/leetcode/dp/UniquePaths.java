package xray.leetcode.dp;

/*
 * 
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if(m<=0||n<=0){
            return 0;
        }
        int[][] dp = new int[m][n]; //number of ways to reach [i][j]
        dp[0][0] = 1; //TP initialize 0 0
        
        for(int i=1;i<m;i++){ //TP initialize first row 
            dp[i][0] = 1;
        }
        
        for(int j=1;j<n;j++){ //TP initialize first col
            dp[0][j] = 1;
        }
        
        //TIP the dp on ways, so plus
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}	
