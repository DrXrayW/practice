package xray.leetcode.string;

/*
 * IN SHORT:
 * 1. sign 
 * 2. res +=*10 + digit
 * 3. handle overflow
 * 4. Character calls 
 */
public class StringToInteger_atoi01 {
    public int atoi(String str) {
        
        if((str==null)||(str.isEmpty())){
            return 0;
        }
        
        String s = str.trim();
        
        int i = 0;
        int sign = 1; //deal with sign
        
        if(s.charAt(i)=='+'){
        	i++;
        }else if(s.charAt(i)=='-'){
        	i++;
        	sign = -1;
        }
        
        long result = 0;
        while(i<s.length()){
        	if( (s.charAt(i) < '0') || (s.charAt(i) > '9') ){
        		break; //do not break
        	}
        	int n = Character.getNumericValue(s.charAt(i));  //this is a useful call
            result = result * 10 + n;
            if( result> ((long)Integer.MAX_VALUE + 1) ) { //overflow handling, needs to ask
                break;
            }
            i++;
        }
        
        result *= sign;
        
        if(result<Integer.MIN_VALUE){  //overflow handling, needs to ask
            result = Integer.MIN_VALUE;
        }
        
        if(result>Integer.MAX_VALUE){ //overflow handling, needs to ask
            result = Integer.MAX_VALUE;
        }

        return (int)(result);
    }
}
