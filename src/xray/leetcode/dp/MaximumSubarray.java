package xray.leetcode.dp;

/*
 * DP
 * 
 * at each position, two choices: start over or continue
 * 
 * so function f is the max of the two
 * 
 * So for DP, if there are two choices, then we need to maintain 2 vars
 */
public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        if(A==null||A.length==0){
            return -1; 
        }
        
        int sum = A[0];
        int max  = A[0];
        for(int i=1;i<A.length;i++){
            sum = Math.max(sum+A[i], A[i]);
            max = Math.max(max, sum);
        }
        return max;
    }
}
