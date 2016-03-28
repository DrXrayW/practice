package xray.leetcode.string;

public class CompareVersionNumbers01 {
    /*
    this does not use split
    
    Reader version
    */
    public int compareVersion(String version1, String version2) {
        VersionReader v1 = new VersionReader(version1);
        VersionReader v2 = new VersionReader(version2);
        while( v1.hasNext() || v2.hasNext() ){
            int val1 = v1.read();
            int val2 = v2.read();
            if(val1>val2){
                return 1;
            }else if(val1<val2){
                return -1;
            }//else next
        }
        return 0;
    }
    
    class VersionReader{
        //add getter and setter
        String ver;
        int i = 0;
        int read(){
            int value = 0;
            if(!hasNext()){ //this if block is not necessary, but to be clear and avoid unnecessary operations
                return value;
            }
            int j = i;   //j nees to start at i, 
            while(j<ver.length()&&ver.charAt(j)!='.'){
                j++;
            }
            if(i<j){ //we have a token with length > 0
                value = Integer.parseInt(ver.substring(i,j));
            }
            i=j+1;
            return value;
        }
        boolean hasNext(){
            return i<ver.length();
        }
        VersionReader(String ver){
            this.ver = ver;
        }
    }   
}
