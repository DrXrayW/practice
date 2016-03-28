package xray.leetcode.string;

public class ReadNCharactersGivenRead4 extends Reader4 {
    /*
     * we do not need to save the unused ones in a buffer
     */
    public int read(char[] buf, int n) {
        if(n<=0){
            return 0;
        }
        /*
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
        int readBytes = 0;
        boolean eof = false;
        while(!eof&&readBytes<n){
            char[] buf4 = new char[4];
            int readCount = read4(buf4);
            if(readCount<4){
                eof = true;
            }
            int bytes = Math.min(n - readBytes, readCount); //readCount, or max reads needs now
            //src, srs_pos, des, des_pos, length
            System.arraycopy(buf4, 0, buf, readBytes, bytes);
            readBytes+=bytes;
        }
        return readBytes;
    }
}
