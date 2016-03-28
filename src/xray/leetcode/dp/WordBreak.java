package xray.leetcode.dp;
import java.util.*;
/*
 * 
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".



 * DP general IDEA:
 * 
 * 1. What kind of historical information to save, its data structure
 * 2. The recursion function.
 * 3. Initialization 
 *
 * 
 * here:
 * 
 * dp[i]: whether word i-1 is breakable
 * 
 * recursion, for each new i (end), search all the substring ending at i, 
 *           starting from 0 to end, if the string is a word, then if the part before start is breakable then we know the current index is breakable.
 *           so we want to know about the position before start, i.e. start - 1, so taking dp[start].
 *           
 *           Note that the result of current end, should be set at end + 1. 
 *           
 *           This is because we want to include the first char case: 
 *           if the current whole word (till the current end/i) is in the dictionary, then we say it is true.
 *    
 *        
 *    s:   0 1 2 3 4 5 6 7 8 
 *        
 *   dp:  0 1 2 3 4 5 6 7 8 9 
 *                          
 *    
 * O(n^2) time,  O(n) space
 */
public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s==null||s.length()==0){
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len+1];  //dp[i]: whether word [0, i-1] is breakable, init "" is breakable, which is dp[-1]
        dp[0] = true; //-1 for 0
        
        for(int end=0;end<len;end++){
            for(int start=0;start<=end;start++){
                String sub = s.substring(start, end+1);
                if(dict.contains(sub)){
                    if(dp[start]){
                        dp[end+1] = true;
                        break;
                    }
                }
            }
        }        

        return dp[len];
    }
}
