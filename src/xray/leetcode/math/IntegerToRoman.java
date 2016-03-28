package xray.leetcode.math;

/*
 * IDEA use a descendant ordered number to symbol array 
 * deduct from large, and append to result
 * 
 */
public class IntegerToRoman {
    static final int radix[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static final String symbol[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int n = num;
        while(n>0){
            for(int i=0;i<radix.length;i++){
                if(n>=radix[i]){
                    res.append(symbol[i]);
                    n-=radix[i];
                    break;
                }
            }
        }
        return res.toString();
    }
}
