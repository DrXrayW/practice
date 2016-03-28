package xray.leetcode.dp;
/*
 * questions:
 * empty? 
 * null?
 * negtive
 * 
 */
public class MaximumProductSubarray01 {
    public int maxProduct(int[] A) {
        int min = A[0];
        int max = A[0];
        int res = A[0];
        for(int i=1;i<A.length;i++){
            int premin = min;
            int premax = max;
            min = Math.min(Math.min(A[i]*premin, A[i]*premax), A[i]);
            max = Math.max(Math.max(A[i]*premin, A[i]*premax), A[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
