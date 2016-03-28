package xray.leetcode.string;


public class RegularExpressionMatching01 {
	public static void main(String[] args) {
		RegularExpressionMatching01 s = new RegularExpressionMatching01();
		
		boolean  x = s.isMatch("aaa", "ab*a");
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
        if (p.length()>1 && ('*' == p.charAt(1)) ) {
            return (isMatch(s, p.substring(2)) //x* matches empty string 
            		|| matchFirst(s, p) && isMatch(s.substring(1), p)); //or at least one x 
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
