package xray.leetcode.dp;

/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {
	public static void main(String[] args) {
		InterleavingString s = new InterleavingString();
		boolean x = s.isInterleave("", "b", "b");
		return;
	}

    public boolean isInterleave(String s1, String s2, String s3) {
        //dp[i][j] = taking s1[i-1]s2[j-1];
        
        if(s1==null||s2==null||s3==null){
            return false;
        }
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if( (len1+len2)!=len3 ){
            return false;
        }
        
        if(len3==0){
            return true;
        }
        
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0]  = true;
        
        for(int i=1;i<len1+1;i++){
            dp[i][0] = s1.charAt(i-1) == s3.charAt(i-1);
        }
        
        for(int j=1;j<len2+1;j++){
            dp[0][j] = s2.charAt(j-1) == s3.charAt(j-1);
        }
        
        for(int i=1;i<len1+1;i++){
            for(int j=1;j<len2+1;j++){
                //postion at s3: i + j -1, already taken 0 to i-1, and 0 to j-1, that is i and j length, now we are looking at the (i+j+1) pos
                //which is i+j
                char c3 = s3.charAt(i+j-1);
                boolean res = false;
                if(c3==s1.charAt(i-1)){
                    res = res || dp[i-1][j];
                }
                if(c3==s2.charAt(j-1)){
                    res = res || dp[i][j-1];
                }
                dp[i][j] = res;
            }
        }

        return dp[len1][len2];
    }
}
