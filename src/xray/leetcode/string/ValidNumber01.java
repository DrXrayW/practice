package xray.leetcode.string;

/*
 * the idea is to set isNumeric to false, when it is in an intermediate status
 */
public class ValidNumber01 {
    public boolean isNumber(String s) {
    	int i = 0, n = s.length();
    	while (i < n && Character.isWhitespace(s.charAt(i))){ //ok with leading white spaces, skiping them
    		i++;
    	}
    	if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')){ //ok with + or - if there is one
    		i++;
    	}
    	boolean isNumeric = false;
    	while (i < n && Character.isDigit(s.charAt(i))) { 
	    	i++;
	    	isNumeric = true; //either go with digit
    	}
    	if (i < n && s.charAt(i) == '.') { 
	    	i++;
	    	while (i < n && Character.isDigit(s.charAt(i))) { //or . before digits
		    	i++;
		    	isNumeric = true;
	    	}
    	}
    	//e part begins
    	if (isNumeric && i < n && s.charAt(i) == 'e') { 
	    	i++;
	    	isNumeric = false;
	    	if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')){  //e may follow with + or - or nothing
	    		i++;
	    	}
	    	while (i < n && Character.isDigit(s.charAt(i))) { //but after e must be digit
		    	i++;
		    	isNumeric = true;
	    	}
    	}
    	//e part ends
    	while (i < n && Character.isWhitespace(s.charAt(i))){ //again whitespaces
    		i++;
    	}
    	return isNumeric && i == n;
    }
}
