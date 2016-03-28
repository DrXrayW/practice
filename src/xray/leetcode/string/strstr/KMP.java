package xray.leetcode.string.strstr;

public class KMP {
	
	public static void main(String[] args) {
		KMP s = new KMP();
		String pattern = "abcdabcdabcd";
		int plen = pattern.length();
		int[] next = new int[plen];
		next = s.buildNextArray(pattern, next);

		return;
				
	}
	
	public int strStr(String haystack, String needle) {
		return kmpStrStr(haystack, needle);
	}

	private int kmpStrStr(String haystack, String needle) {
		if(haystack==null||needle==null){
			return -1;
		}
		
		int slen = haystack.length();
		int plen = needle.length();
		if(slen<plen){
			return -1;
		}
		
		if(plen==0){
			return 0;
		}
		
		int[] next = new int[plen];
		next = buildNextArray(needle, next);
		
		return kmpSearch(haystack, needle, next);
	}

	public int[] buildNextArray(String needle, int[] next) {
		next[0] = -1; //skipping
		int scanner = 0;
		int runner = -1;
		int plen = needle.length();
		/*
		 * NOTE: scanner < plen - 1
		 * 
		 * meaning of next: fail at next, you may restart from this index in the pattern instead (or -1 skipping the current pos). 
		 * 		  
		 * 0  1  2  3
		 * a  b  a  d
		 * -1 0  0  1
		 * 
		 * We can only fail at all positions so next[plen]
		 * now we have next[0] = -1
		 * 
		 * because we only mark the next when first reaches a position (regardless of what the char is (even in in optimization approach))
		 * 
		 * the thing decides whether we can reach a position is the prefix excluding the current pattern char
		 * 
		 * so we have 1 less in number, but the first one is -1, so 1 less in loop but plen in array
		 * 
		 */
		
		while(scanner<plen-1){ 
			if(runner==-1||needle.charAt(runner) == needle.charAt(scanner)) { //skipping or same
				scanner++;
				runner++;
				//mark next
				next[scanner] = runner;
			}else{
				runner = next[runner];
			}
		}
		return next;
	}

	private int kmpSearch(String haystack, String needle, int[] next) {
		int scanner = 0;
		int runner = 0;
		int slen = haystack.length();
		int plen = needle.length();
		while(runner<plen&&scanner<slen){
			if(runner == -1||haystack.charAt(scanner) == needle.charAt(runner) ){ //skipping or same
				scanner++;
				runner++;
			}else{
				runner = next[runner];
			}
		}

		/*
		 * 0 1 2 3 4
		 *         ^
		 *         scanner
		 *     p0p1
		 *      
		 */
		if(runner==plen){
			return (scanner - plen);  
		}else{//fail at end of s
			return -1;
		}
	}
}
