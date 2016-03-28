package xray.leetcode.bits;

public class SingleNumberII01 {
	/*
	 * since all the bits are the same, so lets just think of one bit
	 * 
	 * x0 = 0 means has seen 1 for at least 1 time!!
	 * 
	 * x0 = 1
	 * x0 & ~A[i] 
	 * x0 = 0, A[i] = 0, -> 0
	 * x0 = 0, A[i] = 1, -> 0
	 * x0 = 1, A[i] = 1, -> 0
	 * x0 = 1, A[i] = 0, -> 1
	 * 
	 * so x1=0;
	 * x0 & A[i] means first time seen 1 on x0
	 * the x1 part at this time is 0, so always 0
	 * 
	 * So x1 is set to 1 for the first time when x0 first see 1
	 * 
	 * similarly to x0, x1 will keep at 1, until the next 1 is seen. 
	 * which means the 2nd 1
	 * 
	 * so with this formula, the 1 is pushing up from x0 to x(k-1) in around
	 * 
	 * NOTICE x1 means seen for once, so that is why return x1
	 * 
	 * so each flag will keep 0 until the previous flag is 1 and a new 1 is seen
	 * 
	 */
    public int singleNumber(int[] A) {
        if (A == null) return 0;
        int x0=~0, x1=0, x2=0;
        for(int i=0;i<A.length;i++){
            int t = x2;
            int d = A[i];
            x2 = (x1&d)|(x2&~d);
            x1 = (x0&d)|(x1&~d);
            x0 = (t&d)|(x0&~d);
        }
        return x1;
    }
}
