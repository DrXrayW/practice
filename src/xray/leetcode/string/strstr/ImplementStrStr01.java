package xray.leetcode.string.strstr;

/*
 * brutal implementation
 */
public class ImplementStrStr01 {
	public int strStr(String haystack, String needle) {
		for (int i = 0; ; i++) {
			for (int j = 0; ; j++) {
				if (j == needle.length()){
					return i; //reaches end of need, return
				}
				if (i + j == haystack.length()){
					return -1; //end but no found
				}
				if (needle.charAt(j) != haystack.charAt(i + j)){
					break; //next i
				}
			}
		}
	}
}
