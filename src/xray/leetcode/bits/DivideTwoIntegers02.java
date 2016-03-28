package xray.leetcode.bits;

/*
 * 
 * IN SHORT:
 * Do not go for the strict manner, 
 * Deduct from shifting 0 to max possible, each time add 1 << shift.
 * Until the remaining is smaller than the divisor
 * 
 *ask about negative
 * 
 * Mind overflow when getting abs. //ask this question
 * 
 */
public class DivideTwoIntegers02 {
	public int divide(int dividend, int divisor) {
		if(divisor==0){
			throw new IllegalArgumentException("divisor is zero.");
		}

		boolean sign = getSign(dividend, divisor); 
				
		long dvdnd = (long) dividend;
		long dvsr = (long) divisor;
		
		dvdnd = Math.abs(dvdnd);
		dvsr = Math.abs(dvsr);
		
		long result = divideAbs(dvdnd, dvsr);
		result = sign? result : -result;
		result = Math.max(result, Integer.MIN_VALUE);
		result = Math.min(result, Integer.MAX_VALUE);
		return (int)result;
	}
	
	private long divideAbs(long dvdnd, long dvsr) {
		
		long r = dvdnd;
		long result = 0;
		while(r >= dvsr){ //when dvdnd is zero, it won't go through this loop and therefore 0
			long deduct = dvsr;
			int q = 1;
			while(deduct <= r){
				r -= deduct;
				result += q;
				q=q<<1;
				deduct = deduct <<1;
			}
		}

		return result;
	}

	public boolean getSign(int num1, int num2){
		return  ( (num1^num2)>>31 ) == 0;
	}
}