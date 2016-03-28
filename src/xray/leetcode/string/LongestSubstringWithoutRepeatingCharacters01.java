package xray.leetcode.string;

import java.util.*;

/*
 * IDEA use a last seen for each char to roll forward, 
 * (because we are including the current char anyways, we have to exclude the part ending at the previous dup char)
 * so that the current start can be be moved to last + 1 (the last char that is a dup for the current) if a dup is found
 * 
 * O(n) runtime, O(1) space
 */
public class LongestSubstringWithoutRepeatingCharacters01 {
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
            
            //in our current scan, dup
            if(last>=start){
                //update 
                start = last + 1; //TIP it really needs to be the last 
            }
            
            //get length
            //TIP: start is inclusive, so that means X - start, X should be exclusive
            //[start] [...] [i-1] [i]
            
            /*
             * TIP: here, either no dup, so it is start to current length
             * or
             * there was dup, but start has been updated to last + 1, which is the correct length of current str
             * 
             * with this, there is no need to spit out what is left in the end
             */
            
            max = Math.max(max, i-start+1);                

            lastIndex.put(c, i);
        }
        return max;
    }
}
