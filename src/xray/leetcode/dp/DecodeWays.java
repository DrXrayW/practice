package xray.leetcode.dp;

public class DecodeWays {
	/*
	 * 
	 * 
	 * f(0) = 1
	 * f(1,s) = if(s is 1 - 9, then 1, or 0) = g(1,s)
	 * f(2,s) = f(1,s0)g(1,s1) + g(2,s)combine 
	 * g(2,s) = if(s is 10-26, then 1, or 0);
	 * f(k,s) = f(k-2)*f(2, s)combine, f(k-1)*f(1,s)   
	 * 
	 * For a new char scanned, 
	 * 
	 * It is either combined with the previous one, or not
	 * 
	 * so f(k) = f(k-2) * g(2) + f(k-1) * g(1)
	 * 
	 *  g(2) ways for the last 2 to be a combined number, 10-16 then 1, otherwise 0
	 *  g(1) ways for the last 1 to be a combined number, 1-9 then 1, otherwise 0
	 * 
	 */
	
    public int numDecodings(String s) {
        int num = 1;
        if(s==null||s.isEmpty()){
            return 0;
        }
        int num_k_2 = 1; //k - 2  //TIP 1 is the safe number here, when used before the 3rd number, 
        int num_k_1 = 1; //k - 1
        for(int i=0;i<s.length();i++){
            num = num_k_2 * numDecodings(s, 2, i) + 
                    num_k_1 * numDecodings(s, 1, i); //note that there is no need to break down to two single digits as k-1 has covered it
            
            if(num==0){ //optimization TIP when there is no way for this long, it is no way for the whole length
            	break;
            }
            
            num_k_2 = num_k_1;
            num_k_1 = num;
        }
        return num;
    }

    /**
     * the numbers of encodings in last length of s ending at i
     * 
     * @param s			
     * @param length	
     * @param i  		last index we are looking at
     * @return
     */
    private int numDecodings(String s, int length, int i){
        assert(length==1||length==2);
        
        //the way of the char at i
        if(length==1){
            char c = s.charAt(i);
            return c>='1'&&c<='9' ? 1 : 0;
        }
        //length == 2
        
        //calculate the way combining the last two chars at i-1 and i
        if(i<1){
            return 0; //not enough length, no way
        }
        char c1 = s.charAt(i-1); 
        char c2 = s.charAt(i);
        int num = (c1-'0')*10+(c2-'0');
        if( (num>=10)&&(num<=26) ){
            return 1;
        }else{
            return 0;
        }
    }
}
