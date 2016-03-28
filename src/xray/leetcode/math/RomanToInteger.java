package xray.leetcode.math;

public class RomanToInteger {
    private int map(char c) {
        switch (c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
        }
    }
    //When a situation like IV, i.e. the later one is greater than the previous one, the previous one should be deducted instead of being added.
    public int romanToInt(String s) {
        int result = 0;
        for(int i=0;i<s.length();i++){
            if( (i>0) && 
                (   map(s.charAt(i)) > map(s.charAt(i-1))   ) ){
                result = result - 2*map(s.charAt(i-1)); //correct the mis add, into deduct so - 2*
            }
            result += map(s.charAt(i));
        }
        return result;
    }
}
