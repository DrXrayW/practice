package xray.leetcode.string;

import java.util.*;

/*
 * IDEA use a last seen for each char to roll forward, 
 * so that the current start can be be moved to last + 1 (the last char that is a dup for the current) if a dup is found
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        if(s==null||s.isEmpty()){
            return max;
        }
        Map<Character, Integer> lastIndex = new HashMap<Character, Integer>();
        int start = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            Integer last = lastIndex.get(c);
            if(last==null){
                last = -1;
            }
            if(last>=start){
                //in our current scan, dup
                
                //get length
                //TIP: start is inclusive, so that means X - start, X should be exclusive
                //[start] [...] [i-1] (i)
                
                max = Math.max(max, i-start);                
                
                //update 
                start = last + 1; //TIP it really needs to be the last 
            }
            lastIndex.put(c, i);
        }
        max = Math.max(max, s.length() - start); //TIP: remember to spit out the last time :)  s.length() is exclusive so here it is
        return max;
    }
}
