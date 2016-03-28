package xray.leetcode.string;


public class RegularExpressionMatching02 {
	public static void main(String[] args) {
		RegularExpressionMatching02 s = new RegularExpressionMatching02();
		
		boolean  x = s.isMatch("aa", "ab+a");
		System.out.println(x);
		return;
	}

    public boolean isMatch(String s, String p) {
    	//handle null
    	if(s==null||p==null){
    		return false; // or exception
    	}
    	if(s.isEmpty()&&p.isEmpty()){ //condition 1
    		return true;
    	}
        if(p.length()>1){
        	if('*' == p.charAt(1))  {
                return (isMatch(s, p.substring(2)) //x* matches empty string 
                		|| matchFirst(s, p) && isMatch(s.substring(1), p)); //or at least one x
        	}
        	if('+' == p.charAt(1)) {
                return matchFirst(s, p) && ( //x+ matches at least one x
                		isMatch(s.substring(1), p.substring(2)) || //our options are skiping the pattern
                		isMatch(s.substring(1), p) );  // or not skipping the pattern
        	}
        }
        if (s.isEmpty()){ 
        	return false; //p cannot be empty, as it passed condition 1, also there is no * to protect p, so fail
        }
        return matchFirst(s,p)? isMatch(s.substring(1), p.substring(1)) : false;
    }
    
    private boolean matchFirst(String s, String p){
    	return (!s.isEmpty()) &&(!p.isEmpty()) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
    }
}
