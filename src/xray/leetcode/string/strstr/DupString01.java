package xray.leetcode.string.strstr;

/*
 * 13确实用 kmp简单，和那个 storm8 的题目一样
pattern = S
source = S + S
找 pattern 在 source中的位置，如果某个 位置 i 使得 i 整除 |S|, 那么就
return
true, 否则 return false


or use kmp function array directly

this impl favors smaller chunks over larger chunks: therefore favors more chunks
 */
public class DupString01 {
	public static void main(String[] args) {
		output("abaabab"); 
		output("abaabaabaaba"); //repeating char as prefix and suffix in the pattern
		output("aaasaaa");
		output("aaabaaab");
		output("aaabaaabaaabaaabaaabaaabaaabaaab");
		output("abcdabcdabcdabcd");
		output("abcdeabcde");
		output("a");
		output("aa");
		output("abcdxabcd");
		return;
				
	}
	
	private static void output(String input) {
		DupString01 s = new DupString01();
		int x = s.maxsub(input);
		System.out.println("[" +input +"]:" + x);
	}
	private int maxsub(String s){
		if(s==null){
			return 0;
		}
		int len = s.length();
		if(len<=1){
			return len;
		}
		/*
		 * dp is the length of the longest prefix that matches the part ending at this position
		 * 
		 * like kmp but the number means count, not index, so like kmp + 1
		 * 
		 * also, no optimization, just prefix length calculation
		 * 
		 * init as all 0
		 */
		int[] dp = new int[len];  
		
		/*
		 * here dp[i] is the length of matching prefix to the current position, which is also the next char index to match going forward 
		 * 
		 * e.g. 
		 * 
		 * 0123456
		 * aaabaaa
		 *     aaa 
		 * 
		 * dp[6] = 3    
		 *     
		 * 
		 * so the matching index is actually at 3 - 1, dp[2] 
		 * 
		 *       0   1   2   3   4   5   6
		 *       a   b   a   a   b   a   b
		 * dp    0   0   1   1   2   3   3->1 match so = 1 + 1, note that the final lookup won't change the value of j, so the final position we are at is actually 3
		 * 
		 *  so that is why when j>0, it is a real match, only when j==0, it is a failure
		 *
		 *  
		 * 
		 */
		
		//dp[0] = 0; init
		for(int i=1;i<len;i++){
			int j = i;
			while(j>0&&s.charAt(i)!=s.charAt(dp[j-1])){
				j = dp[j-1];
			}
			dp[i] = j > 0 ? dp[j-1] + 1 : 0;
		}
		
		/*
		for(int i=1;i<len;i++){
			if(s.charAt(i)==s.charAt(dp[i-1])){  //if skipping and not matching, then reset to skip, end of local max 
				dp[i] = dp[i-1] + 1;
			}else{
				//could try the alternative of dp[i-1], that is why we need a j
			}
			
		}
		
		j>0 to make sure we do not go further from the first element
		
		*/
		
		int j = dp[len-1]; //max length of the suffix and prefix match
		
		/*
		 * the last suffix is known to match the prefix
		 *
		 * len - j = the length of the part excluding the suffix, 
		 * 		when len - j == j, it is an exact half match
		 * 		when len - j > j, the part at least includes the equal prefix
		 * 		when len - j < j, that is the max prefix + max suffix > s, that means there are overlapping part between prefix and suffix
		 * 				s1 | s2 | s3
		 *         s1 + s2 = s2 + s3
		 *         
		 *         we must have |s1| == |s3| , denote wlen
		 *         so the first |s1| chars of s2 must equal to s1
		 *            the last  |s3| chars of s2 must equal to s3
		 *            
		 *         also s2 must be a repetitive s1/s3, because it needs to match to itself shifting right/left wlen,
		 *          
		 *         so we have s1 = s3 and it repeats itself in s2. s1 or s3 is the smallest repeating pattern (because s2 is maximized).   
		 * 
		 *  len%(len-j)!=0
		 *  	when len - j == j
		 *  	when len - j < j
		 *  		we know it will be 0, that would be a break;
		 *  	so here we only have
		 *  		when len - j > j
		 *  		that equals to len%(len-j)!=0, because when len - j > 1/2 len, there is no way len - j can be divided without remainder 
		 *  		remember the meaning: we have a known matching prefix and a suffix, they do not overlap, we have a part in between.   
		 *  
		 *  j = dp[j-1]
		 *  	
		 *  	so we cut the last suffix (that matches the prefix)
		 *  	
		 *  	
		 */
		
		int wlen = len - j;
		
		if(wlen > j){  //with the analysis above we do not need while here
			wlen = len;
		}
		
		/*
		 * when it is reduced to 
		 *  	when len - j == j
		 *  	when len - j < j
		 *			we know that len - j is the length of the pattern
		 * 
		 */
		
		return len/wlen;
		
		/*
		 * if want to get the longest repeating string except the string itself. 
		 * 
		 * now we know the shortest one. say n = s.length()
		 * p = pattern.length()
		 * 
		 * we must have n = kp
		 * 
		 * so what we want is n = x ( y p), so that x * y = k
		 * we want x to be the min value > 1
		 * 
		 * that become a question to find the min factor of k
		 * 
		 * int d = 2 to sqr(k) + 1, find k%d==0
		 * 
		 * 
		 */
	}
}
