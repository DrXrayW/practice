package xray.leetcode.dp;
//IN SHORT: fabonacci, rolling pre and cur

public class ClimbingStairs {
    public int climbStairs(int n) {
        //consider the case from n-1 step to n step:
        //F(n) = F(n-1) + F(n-2)
        //Fabonacci
        
        // -1 0 1 2 3 4 5 
        //  0 1 1 2 3 5
        int pre = 0;
        int cur = 1;
        for(int i=0;i<n;i++){
            int tmp = cur;
            cur = pre + cur;
            pre = tmp;
        }
        return cur;
    }
}
