package xray.leetcode.string;

public class ValidPalindrome {
	
	
    public boolean isPalindrome(String s) {
    	String t = clean(s); //if the string is not clean
    	if(t.isEmpty()){
    		return true;
    	}
    	
    	int l = t.length();
    	boolean odd = (l % 2) !=0;
    	
    	//even
    	//0 1 2 3 4 5 (l = 6)
   	
    	//odd
    	//0 1 2 3 4 (l = 5)
    	
    	int left = l/2 - 1;
    	int right = l/2;
    	if(odd){
    		right ++;
    	}
    	
    	while(left >= 0){
    		if(t.charAt(left) != t.charAt(right)){
    			return false;
    		}
    		left --;
    		right ++;
    	}
    	
    	return true;
    }
    
    private String clean(String s){
    	return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
