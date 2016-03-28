package xray.leetcode.dp;

/*
 * converted to use O(n)
 */
public class UniquePaths01 {
    public int uniquePaths(int m, int n) {
        if(m<=0||n<=0){
            return 0;
        }
        int[] dp = new int[n]; //number of ways to reach [i][j]
        dp[0] = 1; //TP initialize 0 0
        
        //TIP the dp on ways, so plus
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
            	if(i==0||j==0){
            		dp[j]=1;
            	}else{
            		dp[j] = dp[j] + dp[j-1]; //dp[j-1] is current, dp[j] is the previous line
            	}
            }
        }
        
        return dp[n-1];
    }
}	
