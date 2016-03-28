package xray.leetcode.math;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        //does 0 count? no!
        //does - count? no!
        
        if(x<0){
            return false;
        }
        
        int l = 0;
        int r = x;
        while(r>0){
            r = r/10;
            l++;
        }
        
        //l is the count of digits
        
        //0 1 2 3 4 : 5 
        //0 1 2 3   : 4

        for(int i=0;i<=(l/2-1);i++){
            int d1 = getDigit(x, i);
            int d2 = getDigit(x, l-1-i);
            if(d1!=d2){
                return false;
            }
        }
        return true;
    }
    
    private int getDigit(int n, int i){
    	int divisor = ((int)Math.pow(10, i));
        int r = n / divisor;
        return r % 10;
    }
}
