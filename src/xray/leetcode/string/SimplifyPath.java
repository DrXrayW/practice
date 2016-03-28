package xray.leetcode.string;
import java.util.*;
/*
 * 
 * IDEA
 * use a stringbuilder to save tokens: 
 * 	append anything to stringbuilder until we meet a /, where if the stringbuilder is not empty, then process the token
 *  since we are excluding dup /, this step will check if the previous one is / and this one is still / then ignore the current loop
 *   
 * Use a deque to save the tokens, remove last on ..  do nothing on . save anything else
 * 
 *  Last step pop the deque from head: using removeFirst
 *  
 *  A TIP on the final build, starts with root /  append anything then /, and remove the last one
 *  However, if there is only root, do not remove the last one (or string length>1)
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> s = new LinkedList<String>();
        
        StringBuilder d = new StringBuilder();
        for(int i=0;i<path.length();i++){
        	char c = path.charAt(i);
            if(c=='/'){
            	if((i>1)&&(path.charAt(i)==path.charAt(i-1))){
            		continue; //ignore dup /
            	}
                process(d, s);
            }else{
            	d.append(c);
            }
        }
        process(d, s);
        return getPath(s);
    }
    
    private void process(StringBuilder d, Deque<String> s) {
    	if(d.length()!=0){
	    	String token = d.toString();
	    	if(token.equals(".")){
	    		//ignore
	    	}else if(token.equals("..")){
	            if(!s.isEmpty()){
	                s.removeLast();
	            }
	    	}else{
	    		s.addLast(token);
	    	}
	    	d.setLength(0); //tip clear string builder
    	}
	}

	private String getPath(Deque<String> s){
        StringBuilder b = new StringBuilder("/");
        while(!s.isEmpty()){
            String d = s.removeFirst();
            b.append(d);
            b.append("/");
        }
        if(b.length()>1){
            b.deleteCharAt(b.length()-1);
        }
        return b.toString();
    }
    
}
