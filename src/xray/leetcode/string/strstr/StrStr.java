package xray.leetcode.string.strstr;

public class StrStr {
	public int strStr(String haystack, String needle) {
		for (int i = 0; i<=haystack.length() - needle.length(); i++) {
			boolean match = true;
			for (int j = 0; j<needle.length(); j++) {
				if (needle.charAt(j) != haystack.charAt(i + j)){
					match = false;
					break;
				}
			}
			if(match){
				return i;
			}
		}
		return -1;
	}
}
