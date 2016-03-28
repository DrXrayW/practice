package xray.leetcode.math;
import java.util.*;
public class FractiontoRecurringDecimal {
    /*
    The key insight here is to notice that once the remainder starts repeating, so does the divided result.
    
    1. determine sign output - if negative
    2. determine whether it is integer, by looking at remainder n % d, if integer then just return
     or we need to add the decimal part
    3. build a map for remainders,
     	while(r>=0)
     		if(r is in cache){
     			buf.insert(cache.get(r), "(");
     			buf.append(")");
     		}
	     	remember remainder buf index (buf length): put(remainder, buf.length())
	     	get 10 times of r
	     	r*=10;
	     	buf.append(r/d);
	     	r=r%d;
	     }
    */
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0){
            return null;
        }
        if (numerator == 0){
            return "0";
        }
        StringBuilder buf = new StringBuilder();
        Long n = new Long(numerator);
        Long d = new Long(denominator);
        // negative or positive
        if (n*d < 0){
            buf.append("-");
        }
        n = Math.abs(n);
        d = Math.abs(d);
        
        buf.append(Long.toString(n / d));
        // result is integer or float
        Long r = n % d;
        if (r == 0){
            return buf.toString();
        }
        else{
            buf.append(".");
        }
        // deal with the float part
        // key is the remainder, value is the start position of possible repeat numbers
        Map<Long, Integer> cache = new HashMap<Long, Integer>();
         
        while (r > 0) {
            if (cache.containsKey(r)) {
                buf.insert(cache.get(r), "(");
                buf.append(")");
                break;
            }
            cache.put(r, buf.length());
            r *= 10;
            buf.append((r / d));
            r %= d;
        }
        return buf.toString();
    }
}
