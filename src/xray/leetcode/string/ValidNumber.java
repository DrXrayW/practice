package xray.leetcode.string;

public class ValidNumber {
    public boolean isNumber(String s) {
        if(s.trim().isEmpty()){  
            return false;  
        }  
        
        /*
         * not allowing heading 0,  0233.234 except for 0, 0., 0.23443
         * 
         * ^[-+]? : + or -
         * 
         * 0 or 
         * (0)|
         * 
         * 0.\d* or  (note escape d)
         * * (0(\\.\\d*))|
         * 
         * .\d+ or (for a leading . we must see digits, as single dot is invalid)
         * (\\.\\d+)|
         * 
         * 
         * leading with 1-9
         *            
         * ([1-9]\\d*\\.?\\d*)
         * 
         * after this, 
         * 
         * with or without e +-(optional) 
         * 
         * (e[-+]?\\(0|[1-9]\d*)?
         * 
         * 
         * 
         * this would be very complicated
         * 
         */
        //String regex = "^[-+]?((0)|(0(\\.\\d*))|(\\.\\d+)|([1-9]\\d*\\.?\\d*))(e[-+]?\\(0|[1-9]\d*))?$";  
        
        /*
         * allowing 00123
         * 
         * +-, (\d+ with . or not | . \d+), \d*, the part for e 
         * 
         */
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?"; 
        if(s.trim().matches(regex)){  
            return true;  
        }else{  
            return false;  
        }  
    }
}
