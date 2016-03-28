package xray.leetcode.dp;

public class JumpGame {
	/*
	 * DP:
	 * 
	 * maxReach(i) is the max index we can reach from 0 to i;
	 * 
	 * maxReach(i) = max(maxReach(i-1), i+A[i]);
	 * 
	 * it fails for certain i, max reach (i-1) cannot reach to i
	 * 
	 */
    public boolean canJump(int[] A) {
        if( (A==null)||(A.length==0)){
            return true;
        }
        
        int maxReach = 0;
        for(int i=0;i<=maxReach&&i<A.length;i++){ //tip one, i<=maxReach is in the condition
            maxReach = Math.max(maxReach, i+A[i]); //previous maxReach , or this reach
        }
        return maxReach>=A.length-1;
    }
}
