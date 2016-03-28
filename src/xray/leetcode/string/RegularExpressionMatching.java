package xray.leetcode.string;


public class RegularExpressionMatching {
	public static void main(String[] args) {
		RegularExpressionMatching s = new RegularExpressionMatching();
		
		boolean  x = s.isMatch("aaa", "ab*a");
		return;
	}

    public boolean isMatch(String s, String p) {
        if(p.length()==0){
            return s.length()==0;
        }
        if(p.length()==1){
            return (s.length()==1)&&( s.charAt(0) == p.charAt(0) || p.charAt(0) == '.' ); //put s length check first to protect
        }
        
        //next one not star, simply match
        if(p.charAt(1)!='*'){
            if(s.length()==0){
                return false;  //we cannot check s length at the beginning of the loop, as there are different situations
            }
            if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'){
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
        
        //star
        
        /*
        options, match 0, ignore pattern, 
        match 1, match one, then try without the current char with the same pattern
        */
        
       /*this is a tricky one: 
        * the recursion line will be executed once anyways so the first one will cover 0
        * the char matching is actually for whether to go with the next iteration, so that it will control whether we have the i+1 part.
        * 
        *  note that we need this trying option, cannot just go with match 0 and match 1, for they won't cover the other options we have here.
        *  
        */
        
        /*
        while (s.length() > 0
               && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {  
                return true;
            }
            s = s.substring(1);
        }
        */
        
        /*
         * this is more plain one, however details still need more attention
         */
        if(s.length()>0){
        	boolean match;
        	do{
        		if(isMatch(s, p.substring(2))){ //skip the x*
        			return true;
        		}
        		match = (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'); //if match, then take one from s
        		if(match){
        			//prep for next, TIP: must do this when it s a match, do not mess with the string for no match
        			s=s.substring(1);
            		if(s.length()==0){
            			break;
            		}
        		}
        	}
        	while(match);
        }
        
        
        //s.length() == 0, p is a * pattern, then jump ahead, until p is 0 or 1, that is why we need s length check on p = 0, p=1
        return isMatch(s, p.substring(2));
    }
}
