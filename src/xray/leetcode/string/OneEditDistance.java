package xray.leetcode.string;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if(s==null||t==null){
            return false;
        }
        int len1 = s.length();
        int len2 = t.length();
        if(Math.abs(len1-len2)>1){
            return false;
        }
        
        return helper(s, t, 0, 0, 0);
    }
    
    private boolean helper(String s, String t, int si, int ti, int dis){
    	if(si>=0&&si<s.length()&&ti>=0&&ti<t.length()){
            if(s.charAt(si)!=t.charAt(ti)){
            	dis++;
            	if(dis>1){
            		return false;
            	}
            	return helper(s,t,si+1,ti+1,dis) ||helper(s,t,si+1,ti,dis) || helper(s,t,si,ti+1,dis); 
            }else{
            	return helper(s,t,si+1,ti+1,dis);
            }
    	}
    	
    	if(dis==1){
    		return si==s.length()&&ti==t.length();
    	}
        return Math.abs(s.length() - t.length()) == 1;
    }
}
