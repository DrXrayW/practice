package xray.leetcode.string;

public class CompareVersionNumbers {
    /* split version
    1. convert strings into number arrays, using split \\.
    2. scan the arrays comparing each number
    3. using || condition for length judgment, and default a running out to 0 for comparing
    */
    public int compareVersion(String version1, String version2) {
    	String[] v1 = version1.split("\\.");
    	String[] v2 = version2.split("\\.");
    	int len1 = v1.length;
    	int len2 = v2.length;
    	for(int i=0;i<Math.max(len1, len2);i++){
    		int n1 = i<len1 ? Integer.valueOf(v1[i]) : 0;
    		int n2 = i<len2 ? Integer.valueOf(v2[i]) : 0;
    		if(n1>n2){
    			return 1;
    		}else if(n2>n1){
    			return -1;
    		}
    	}
    	return 0;
    }
    
}
