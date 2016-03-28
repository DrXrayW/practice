package xray.leetcode.math;

/*
 * given 123 return 321
 * given -123 return -321
 * 
 * //there are two tricks about overflow: 
 * 1. the abs may be affected 
 * 2. the result may overflow !! 
 * Question must be asked about overflowing result
 */
public class ReverseDigit {
    public int reverse(int x) {
    	//question: for the tailing 0
        long result = 0;
        long r = Math.abs((long)x);
        
        //if not allowing tailing 0
        /*
        if(x!=0&&x%10==0){
        	return 0; //or throw exception
        }
        */
        while(r!=0){
            result = result*10 + r % 10;
            if(overflow(result, x)){
            	return 0;
            }
            r/=10;
        }
        if(x<0){
            result = -result;
        }
        return (int)result;
    }
    
    private boolean overflow(long x, int sign){
    	long cap = (long)Integer.MAX_VALUE; //the abs of integer min is 
    	if(sign<0){
    		cap ++;
    	}
    	return x > cap;
    }
}
