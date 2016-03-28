package xray.leetcode.string;
/*
 * 
 * The key is when we adjust the sliding window to satisfy the invariant, we need a counter
of the number of times each character appears in the substring.

IN SHORT:
sliding window:
start = 0;

maintain a counter for count of char in the current substring
when it is first char, distinct ++
when distinct > 2, over the limit, move start forward, removing counter along the way 
spit out until numDistinct is back to normal 
  
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters01 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
    	int[] count = new int[256]; //char count that the current window string contains
    	int maxDistinct = 2;
    	int start = 0, numDistinct = 0, maxLen = 0;
    	for (int i = 0; i < s.length(); i++) {
	    	if (count[s.charAt(i)] == 0){
	    		numDistinct++;
	    	}
	    	count[s.charAt(i)]++;
	    	while (numDistinct > maxDistinct) { //let start spit out until numDistinct is back to normal
		    	count[s.charAt(start)]--;
		    	if (count[s.charAt(start)] == 0){
		    		numDistinct--;
		    	}
		    	start++;
	    	}
	    	maxLen = Math.max(i - start + 1, maxLen);
    	}
    	return maxLen;
    }
}
