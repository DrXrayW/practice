package xray.leetcode.stackQueue;

import java.util.*;

public class ValidParentheses {
    private static final Map<Character, Character> P_MAP;
    static
    {
        P_MAP = new HashMap<Character, Character>();
        P_MAP.put(')', '(');
        P_MAP.put('}', '{');
        P_MAP.put(']', '[');
    }

    public boolean isValid(String s) {
        if(s==null){
            return true;
        }

        Stack<Character> t = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!t.isEmpty()){
                Character close = P_MAP.get(c);
                if(close!=null){
                    Character top = t.peek();
                    if(close.equals(top)){
                        t.pop();
                        continue; //Important!!! go next char, instead of falling out
                    }else{
                        return false;
                    }
                }
            }
            t.push(c);
        }
        
        return t.isEmpty();
    }
}
