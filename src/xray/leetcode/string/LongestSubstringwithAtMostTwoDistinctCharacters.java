package xray.leetcode.string;
import java.util.*;
public class LongestSubstringwithAtMostTwoDistinctCharacters {
	public static void main(String[] args) {
		LongestSubstringwithAtMostTwoDistinctCharacters s = new LongestSubstringwithAtMostTwoDistinctCharacters();
		int x = s.lengthOfLongestSubstringTwoDistinct("abaccc");
		return;
	}
	/*
	 * 
	 * test cases:
	 * aaaa, 4
	 * abaccc, 4
	 * 
	 * 
	 * This is a sliding window problem. 
	 * 
	 * The point is: what should we do when we move the current window forward?
	 * 
	 * When do we need to do that?
	 * When we have 3 unique chars
	 * 
	 * Now how to efficiently update the window?
	 * We need to move our start to the last char change (before the current one!!)
	 * 
	 * Also, update used chars: since we lost track of the previous one, we may clear and add the new two
	 * 
	 * 2 catches:
	 * 1. update the last change (s.charAt(i-1) != s.charAt(i)) AFTER the update processing, for we need the last one, unless you save it first
	 * 
	 * 2. general for sliding window, dump what is in the window
	 * 
	 */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s==null){
            return 0;
        }
        int len = s.length();
        if(len<=2){
            return len;
        }
        
        int max = 0;
        int lastChangeCharAt = -1;
        Set<Character> used = new HashSet<Character>();
        int start = 0;
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            int preSize = used.size();
            used.add(c);
            int curSize = used.size();
            if(curSize>2){
                //update length
                max = Math.max(max, i-start); //start, inclusive, i, exclusive, no adjust needed
                
                //the two window params must have been set, no need to check
                //update window params
                used.clear();
                used.add(s.charAt(lastChangeCharAt));
                used.add(c);
                
                start = lastChangeCharAt;
            }
            if(i>0&&s.charAt(i-1)!=s.charAt(i)){
                lastChangeCharAt = i;
            }
        }
        //dump window
        max = Math.max(max, len-start);

        return max;
    }
}
