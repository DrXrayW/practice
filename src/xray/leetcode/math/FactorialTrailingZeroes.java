package xray.leetcode.math;

/*
 * Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.

TIP: 
number of 0 is the number of 5
say we have 6: 120, one zero because only one 5
so at 16: it is 15, 10, 5, 3 5's so 3 zeros. 
consider 51, we will have 10 5, but 2 of them 25, 50 contributes 2, so they should be add again.
so the algorithm is to divide by 5, add to the count, iterate until we have 0


 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        assert(n>=0);
        if(n==0){
            return 0; //0! = 1
        }
        int r = n;
        int count = 0;
        while(r>0){
            r = r/5;
            count+=r;
        }
        
        return count;
    }
}
