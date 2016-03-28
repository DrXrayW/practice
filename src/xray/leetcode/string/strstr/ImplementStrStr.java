package xray.leetcode.string.strstr;

/*
 * Java api
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if( (haystack==null)||(needle==null) ) {
            return -1;
        }
        //java api
        return haystack.indexOf(needle);
    }
}
