package xray.leetcode.string.strstr;

public class KMPOptimized {
	
	public static void main(String[] args) {
		KMPOptimized s = new KMPOptimized();
		String pattern = "aaabaaaaaaaab";
		int plen = pattern.length();
		int[] next = new int[plen];
		next = s.buildNextArray(pattern, next);
		int[] next1 = new int[plen];
		next1 = s.buildNextArray01(pattern, next1);
		int[] next2 = new int[plen];
		next2 = s.buildNextArray02(pattern, next2);
		
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

	private int[] buildNextArray(String needle, int[] next) {
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
				/*
				 * OPTIMIZATION: link to next[] if the next runner char is the same as the scanner char
				 */
				if(needle.charAt(scanner) != needle.charAt(runner)){ //ADDED
					next[scanner] = runner; 
				}else{ //ADDED
					next[scanner] = next[runner]; //ADDED
				} //ADDED
				
			}else{
				runner = next[runner];
			}
		}
		return next;
	}
	
	public int[] buildNextArray02(String pattern, int[] next) {
		int plen = pattern.length();
		/*
		for(int i=1;i<plen;i++){
			if(pattern.charAt(i)==pattern.charAt(next[i-1])){
				next[i] = next[i-1] + 1;
			}
		}
		*/
		for(int i=1;i<plen;i++){
			int j = i;
			while(j>0&&pattern.charAt(i)!=pattern.charAt(next[j-1])){  //complexity of this is log k
				j = next[j-1];
			}
			next[i] = j > 0 ? next[j-1] + 1 : 0;
		}
		return next;
	}
	
	public int[] buildNextArray01(String pattern, int[] next) {
		int plen = pattern.length();
		/*
		for(int i=1;i<plen;i++){
			if(pattern.charAt(i)==pattern.charAt(next[i-1])){
				next[i] = next[i-1] + 1;
			}
		}
		*/
		for(int i=1;i<plen;i++){
			int j = i;
			while(j>0&&pattern.charAt(i)!=pattern.charAt(next[j-1])){
				j = next[j-1];
			}
			next[i] = j > 0 ? next[j-1] + 1 : 0;
		}
		
		/*shift right
		 * 
		 * why:
		 * 
		 * because the dp next means: the longest prefix length till this pos, it also means the next possible checking position AFTER THE CURRENT MATCH
		 * 
		 *  while the real next means: which position to check if THE CURRENT POSITION IS THE FIRST MISMATCH
		 *  
		 *  so that is why moving right
		 *  
		 */
		for(int i=plen-1;i>0;i--){
			next[i] = next[i-1];
		}
		next[0] = -1;
		
		
		/*
		 * Optimization, scanning from left to right, if we found that the current char is equal to the recheck, use the recheck one directly
		 */
		
		for(int i=1;i<plen;i++){
			if(pattern.charAt(i) == pattern.charAt(next[i])){
				next[i] = next[next[i]];
			}
		}
		
		return next;
	}


	private int kmpSearch(String haystack, String needle, int[] next) {
		int scanner = 0;
		int runner = 0;
		int slen = haystack.length();
		int plen = needle.length();
		/*
		 * OPTIMIZATION:
		 * 
		 *     scanner
		 *       v
		 * 0 1 2 3 4  slen = 5
		 *     
		 *     0 1 2   plen = 3
		 *       ^
		 *       runner
		 *       
		 * sleft = 5 - 3 = 2 = slen - scanner
		 * pleft = 3 - 1 = 2 = plen - runner
		 * 
		 * 
		 */
		while(runner<plen&&(slen - scanner )>=(plen - runner)){
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
