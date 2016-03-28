package xray.leetcode.enumeration;

import java.util.*;

/*
 * IDEA this is similar to the unique BST one
 * 
 * f(0) = "",
 * f(1) = (), 
 * f(2) = (f(0))f(1) + (f(1))f(0)
 * f(3) = (f(0))f(2) + (f(1))f(1) + (f(2))f(0)
 * ...
 * f(n) = (f(0))f(n-1) + .. + (f(i)) f(n-1-i) .. (f(n-1-i))f(0)
 * 
 * think it this way:
 * Whenever we go ahead construct a new one
 * 
 *  when we still a choice of putting ( or ), 
 *  it is real deciding whether we want more depth of brackets, or we want to keep it balanced. 
 *  we know that f(0) to f(n-1) are all balanced, 
 *  so it like saying, we want the first balance to be at first pair, or 2nd pair or ...
 *  with the big ( ), it sets the limit to the first balance length
 *  
 *  So this is the only way to construct it
 *  
 *  we can prove this can be mapped to a unique BST, but that is less straightforward.
 * 
 * 
 */

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if(n==0){   //TIP remember to handle for n = 0 and n = 1!
            res.add("");
            return res;
        }
        if(n==1){
            res.add("()");
            return res;
        }
        for(int i=0;i<n;i++){  //i from 0 to n - 1
            for(String inner : generateParenthesis(i)){
                for(String outter : generateParenthesis(n-1-i)){
                    StringBuilder buf = new StringBuilder("(");
                    buf.append(inner);
                    buf.append(")");
                    buf.append(outter);
                    res.add(buf.toString());
                }
            }
        }
        return res;
    }
}
