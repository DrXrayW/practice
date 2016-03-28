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
public class DupString {
	public static void main(String[] args) {
		DupString s = new DupString();
		int x = s.maxsub("aaasssaaa");
		//int x = s.maxsub("ababab");
		return;
				
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
		 * this is 
		 */
		
		for(int i=1;i<len;i++){
			int j = i;
			while(j>0&&s.charAt(i)!=s.charAt(dp[j-1])){
				j = dp[j-1];
			}
			dp[i] = j > 0 ? dp[j-1] + 1 : 0;
		}
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
		
		while(len%(len-j)!=0){  
			j = dp[j-1];
		}
		
		/*
		 * when it is reduced to 
		 *  	when len - j == j
		 *  	when len - j < j
		 *			we know that len - j is the length of the pattern
		 * 
		 */
		
		return len/(len-j);
	}
}
