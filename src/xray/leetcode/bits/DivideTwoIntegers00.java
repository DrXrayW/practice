package xray.leetcode.bits;

public class DivideTwoIntegers00 {
    private static int topLimitMask = 0x80000000; 
    
    public int divide(int dividend, int divisor) {
        //case handling
        if(divisor==0){
            throw new IllegalArgumentException("the divisor is zero.");
        }
        
        if(dividend==0){
            return 0;
        }
        boolean addOne = false;
        
        if(dividend == Integer.MIN_VALUE){
            if(divisor == Integer.MIN_VALUE){
                return 1;
            }
            dividend = (dividend + 1);
            addOne = true;
        }else if(divisor == Integer.MIN_VALUE){
            return 0;
        }

        int dividendAbs = dividend>0 ? dividend : -dividend;
        int divisorAbs = divisor>0 ? divisor : - divisor;

        int resultValue = divideAbs(dividendAbs, divisorAbs, addOne);
        
        boolean isNegative =  ( (dividend > 0) && (divisor < 0) ) ||  ( (dividend < 0) && (divisor > 0) );

        int result;
        if(isNegative){
            result = -resultValue;
        }else{
            result = resultValue;
        }
        return result;
    }
    
    private int divideAbs(int dividendAbs, int divisorAbs, boolean addOne){
        
        assert( (dividendAbs>0)&&(divisorAbs>0) );

        int deduct = divisorAbs;
        int shiftCount = 0;
        
        while(!reachTop(deduct)){
            deduct = deduct << 1;
            shiftCount ++;
        }

        int remainder = dividendAbs;
        int result = 0;
        
        for(int shift=0;shift<=shiftCount;shift++){
            if(remainder >= deduct){
                result += (1<< (shiftCount - shift));
                remainder = remainder - deduct;
            }
            
            if(remainder < divisorAbs){
            	if(addOne){
            		remainder ++;
            		addOne = false;
                    if(remainder >= deduct){
                        result += (1<< (shiftCount - shift));
                        remainder = remainder - deduct;
                    }
            	}
            }
            deduct = deduct >> 1;
        }
        
        return result;
    }
    
    private boolean reachTop(int num){
        if( (num & (1<<30)) != 0 ){
            return true;
        }
        
        return false;
    }
}