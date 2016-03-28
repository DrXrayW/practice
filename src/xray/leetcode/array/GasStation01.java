package xray.leetcode.array;

public class GasStation01 {
	/*
	 * 1. starting from any station, we will need to add the gas, then we must go next. 
	 * so it is really gas[i] - cost[i] array, we want an index, such that the sum of all prefixes are >0
	 * 
	 * brutal force would do starting from any one, go through the array, and test along the way, which is O(n^2). 
	 * 
	 * this method starts from any position, for easier handling of the index, start from the end (length - 1)
	 * 
	 *  the idea is to expand the start to left if the sum is lower than 0, 
	 *  expand to right if the sum is greater than or equal to zero.  
	 *  
	 *  <-S4 S3 ...S2...  S1 ...S0 E0 ... E1 E2 ->
	 *  
	 *   1. When we have sum >= 0, we know that all the sums of the prefixes of the array are >=0
	 *     		a. extending right: we are already sum>=0, after expanding sum still >=0, that is exactly our prefixes are fine, and then we are expanding one
	 *          b. extending left: 
	 *          	first, why we are extending left? because the previous S3 to E2 has sum < 0, suppose S2 is the first one of the move (S3 can be S2), 
	 *          		then we have S3 to E2 has sum <0, all the way S2 to E2 has sum < 0
	 *          	second, S2 is the first, then the last move is E1 to E2, so we have S2 to E1 has sum>=0, (and all its prefixes)    
	 *  			
	 *  			S4 ... E2 >=0, 
	 *  			S3 ... E2 <0
	 *  			So:
	 *  			S4>0
	 *  				if S3...S2 >=0 then we have S4 S3 ...S2 >=0
	 *  				else: S3<0
	 *  				
	 *  
	 * 
	 * 
	 * 
	 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = gas.length-1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (start > end) {
           if (sum >= 0) {
              sum += gas[end] - cost[end];
              ++end;
           }
           else {
              --start; //note that we have different processing order for start and end
              sum += gas[start] - cost[start];
           }
        }
        return sum >= 0 ? start : -1;
    }
}

