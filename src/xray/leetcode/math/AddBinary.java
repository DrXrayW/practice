package xray.leetcode.math;

/*
 * IN SHORT:
use carry 
use - '0' to get the number
scan from right to left
use stringbuilder remember to reverse



 */
public class AddBinary {
    public String addBinary(String a, String b) {
        if( (a==null)||(b==null)){
            return null;
        }
        if(a.isEmpty()&&b.isEmpty()){
            return "";
        }
        if(a.isEmpty()){
            return b;
        }
        if(b.isEmpty()){
            return a;
        }
        
        StringBuilder buf = new StringBuilder();
        int bit = 0;
        int carry = 0;
        while( (bit < a.length())||(bit < b.length()) ){
            int ca = 0;
            if(bit<a.length()){
                ca = a.charAt( a.length() -1 - bit) - '0';
            }    
            int cb = 0;
            if(bit<b.length()){
                cb = b.charAt( b.length() -1 - bit) - '0';
            }
            int sum = ca + cb + carry;
            buf.append( (sum % 2) + "");
            carry = sum / 2;
            bit++;
        }
        if(carry>0){
            buf.append("1");
        }
        return buf.reverse().toString(); //this is important
    }
}
