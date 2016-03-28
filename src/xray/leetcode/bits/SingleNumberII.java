package xray.leetcode.bits;

public class SingleNumberII {
	/*
	 * same idea, use bit operation
	 * difference is that we will need extra space so that we can hold for 3 numbers
	 * 
	 * so use 3 vectors for one, two or three, so that each 1 in the vector represents 1, 2 or 3
	 * 
	 */
    public int singleNumber(int[] A) {
        if(A==null||A.length==0){
            //discuss
            //throw exception 
        }
        
        int one = 0;
        int two = 0;
        int three = 0; //this is not exactly three, but 1 when there is something, 0 when there is nothing or it has reached three
        for(int i : A){
            two |= (one & i); //two is 1, if it is already 1(this means it got adds up to 2, which means one & i is not 1 anyways), or the add up to 2,  
            one ^= i;         //one is xor the income
            three = ~(one & two); //three: if you have one and two then clear three to 0; if only have one or two but not both then three is ok to be 1
            //clear one and two if three is cleared (because we have reached three)
            one &= three; 
            two &= three;
        }
        return one;
    }
}
