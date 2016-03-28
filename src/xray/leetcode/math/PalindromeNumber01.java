package xray.leetcode.math;

public class PalindromeNumber01 {
    public boolean isPalindrome(int x) {
        //does 0 count? no!
        //does - count? no!
        
        if(x<0){
            return false;
        }
        
        int d = 1; //find the max base such that x/d is <10
        while(x/d >= 10){
            d*=10;
        }
        
        int r = x;
        while(r>0){
        	int d1 = r / d; //top digit
        	int d2 = r % 10; //bottom digit
            if(d1!= d2){
                return false;
            }
            r = (r % d) /10; //mod getting rid of the first/top digit, divide getting rid of last/bottom
            d /= 100;
        }
        return true;
    }
}
