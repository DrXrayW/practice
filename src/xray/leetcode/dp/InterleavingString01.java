package xray.leetcode.dp;


public class InterleavingString01 {
	public static void main(String[] args) {
		InterleavingString01 s = new InterleavingString01();
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
        
        
        //dp[0][0]  = true;
        
        /*
        for(int i=1;i<len1+1;i++){
            dp[i][0] = s1.charAt(i-1) == s3.charAt(i-1);
        }
        
        for(int j=1;j<len2+1;j++){
            dp[0][j] = s2.charAt(j-1) == s3.charAt(j-1);
        }
        */
        boolean[] pre = new boolean[len2+1];
        for(int i=0;i<len1+1;i++){
        	boolean[] cur = new boolean[len2+1];
            for(int j=0;j<len2+1;j++){
            	if((i==0)&&(j==0)){
            		cur[j] = true;
            	}else if(i==0){
            		cur[j] = s2.charAt(j-1) == s3.charAt(j-1);
            	}else if(j==0){
            		cur[j] = s1.charAt(i-1) == s3.charAt(i-1);
            	}else{
            	
	                //position at s3: i + j -1, already taken 0 to i-1, and 0 to j-1, that is i and j length, now we are looking at the (i+j-1) pos
	                //which is i+j
	                char c3 = s3.charAt(i+j-1);
	                boolean res = false;
	                if(c3==s1.charAt(i-1)){
	                    res = res || pre[j];
	                }
	                if(c3==s2.charAt(j-1)){
	                    res = res || cur[j-1];
	                }
	                cur[j] = res;
	                
            	}
            }
            pre = cur;
        }

        return pre[len2];
    }
}
