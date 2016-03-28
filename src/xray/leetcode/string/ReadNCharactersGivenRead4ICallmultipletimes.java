package xray.leetcode.string;

public class ReadNCharactersGivenRead4ICallmultipletimes extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	
	/*
	 * TIPS:
	 * 
	 * 1. Use a buffer to save the additional reads that is not needed for now
	 * 2. When read count is 0 then finish (the above buffer already takes care of 0<read<4)
	 * 3. Remember to deduct the saved ones from the returned value
	 */
    private StringBuilder b = new StringBuilder(); 
     
    public int read(char[] buf, int n) {
        int total = 0;
        if(n<=0){
            return total;
        }
        
        //handle last time remains
        int start = 0;
        while(b.length()>0&&total<n){
            buf[start] = b.charAt(0);
            b.deleteCharAt(0);
            start++;
            total++;
        }
        
        while(total<n){
            char[] buf4 = new char[4];
            int readCount = read4(buf4);
            if(readCount==0){
                break;
            }
            int cap = n - start;
            int saved = 0;
            for(int i=0;i<readCount;i++){
                if(i>=cap){
                    b.append(buf4[i]);
                    saved ++;
                }else{
                    if( (i+start)<buf.length ){
                        buf[i+start] = buf4[i];
                    }else{
                        //overflow
                    }
                }
            }
            total+=readCount-saved;
            start+=readCount-saved;
        }
        return total;
    }
}
