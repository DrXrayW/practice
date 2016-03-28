package xray.leetcode.dp;

public class JumpGameII {
	/*
	 * DP:
	 * 
	 * maxReach(i) is the max index we can reach from 0 to i;
	 * 
	 * maxReach(i) = max(maxReach(i-1), i+A[i]);
	 * 
	 * it fails for certain i, max reach (i-1) cannot reach to i
	 *
	 * 
	 *          
     * TIP: last cover is the maximum cover with the current step
     * 
     * 
     *  We always have to start from A[0], we can get to whatever is there
     * 
     *   +-----------v 
     *  [0] [1] [2] [3] [4] [5] [6] [7] [8] 
     *   
     *  meaning, with 1 jump, we can reach 0 to 3
     *  
     *  when we are at 4, we want to look at where can we get to from 1 - 3,
     *  we know for sure that 0 cannot reach 4
     *  
     *  so the maxreach until 3, let us know where the 2nd step can maxreach, 
     *  
     *  this continues, so that is why we update last cover like this:
     *  
     *  for a new i cannot be covered, update to maxreach(i-1);
     * 
     */

    public int jump(int[] A) {
        if(A==null||A.length==0){
            return 0; //discuss
        }
        
        int maxcover = 0;
        int step = 0;
        int lastcover = 0;
        for(int i = 0; i<=maxcover&&i<A.length;i++){
            if(i>lastcover){ //last cover can cover until i - 1, won't cover the current i, we need an extra jump
                step++;
                lastcover = maxcover; //but with the additional jump, we can reach to the current maxcover(i-1)
            }
            
            maxcover = Math.max(maxcover, A[i]+i);
        }
        
        if(maxcover<A.length-1){
            return 0; //discuss
        }
        return step;
    }
}
