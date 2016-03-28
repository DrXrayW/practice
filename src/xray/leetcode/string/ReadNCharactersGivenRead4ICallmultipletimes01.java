package xray.leetcode.string;

public class ReadNCharactersGivenRead4ICallmultipletimes01 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	
	/*
	 * IDEA: 
	 * read the buffer if buffer is empty, i.e. work with a prepared buffer, 
	 * 
	 *  maintain offset of buffer, and buffer size
	 *   
	 * 
	 
	 * TIPS:
	 * 
	 * 1. Use a buffer to save the additional reads that is not needed for now
	 * 2. When read count is 0 then finish (the above buffer already takes care of 0<read<4)
	 * 3. Remember to deduct the saved ones from the returned value
	 * 
	 *         /*
         * One trick:
         * the index pointing to the next writing buffer 
         * is the bytes read already
         * 
         *        v
         *  0 1 2 3
         *  d d d
         *  
         * so keeping only one   
         *  
     */

	private char[] buf4 = new char[4];
	private int offset = 0;
	private int bufsize = 0;
    public int read(char[] buf, int n) {
        if(n<=0){
            return 0;
        }
        int readBytes = 0;
        boolean eof = false;
        while(!eof&&readBytes<n){
        	//added
        	if(bufsize==0){ //bufsize is 0, start new
                bufsize = read4(buf4);
                eof = bufsize<4 ;
        	}
        	//if bufsize > 0, read from buf4 first
        	/*
        	 * bufsize:
        	 * if there is already buf, then bufsize = current, 
        	 * otherwise, filled with read, so bufsize = 4
        	 * 
        	 * since we may not want that many, 
        	 * 
        	 * bytes is capped at what we want (n - readBytes) and bufsize
        	 * 
        	 * bytes is the effective number so offset, bufsize and readBytes are all updated with bytes
        	 * 
        	 * offset will go round by 4 so %
        	 */
            int bytes = Math.min(n - readBytes, bufsize); 
            //src, srs pos, des, des pos, length
            System.arraycopy(buf4, offset, buf, readBytes, bytes);
            /* 
             * note that offset and bufsize may not clear when used
             * for n could be 1 or something likewise
             * we have to maintain them
             */
            offset = (offset + bytes) % 4; 
            bufsize -= bytes;
            readBytes+=bytes;
        }
        return readBytes;
    }
}
