package xray.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
 * this doesn't work, because we want the numbers go negative
 */
public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
    	if( S==null || T==null ){  //discuss
    		return null; 
    	}
    	
        if(S.length()==0 || T.length()==0 || S.length()<T.length()){  
        	return "";
        }
        
        Set<Character> chars = new HashSet<Character>(); //to know which chars we care
        Multiset<Character> set = new Multiset<Character>(); //to count, what is left
        for(int i=0; i<T.length(); i++){
        	chars.add(T.charAt(i));
        	set.add(T.charAt(i));
        }
        
        int left = 0;
        int minleft = 0;  //for min length position
        int minlen = S.length()+1; //for the min length
        for(int i=0; i<S.length();i++){
        	char c = S.charAt(i);
            if(chars.contains(c)){
            	if(set.contains(c)){
            		set.remove(c);
            	}
                while(set.isEmpty()){//now cover all characters in T
                    if(minlen>i-left+1){ // record the minimum length and the new minleft, later we need it to return the substring
                        minlen = i-left+1;
                        minleft = left;
                    }
                    if(chars.contains(S.charAt(left))){ //if the left most character in S is also in T
                        set.add(S.charAt(left)); //before move to right, add one to the mapped value(means removed a redundant character if the value still samller than 0 or removed a covered character if the value become bigger than 0)
                    }
                    left++;
                    if(left<=i){
                        if(chars.contains(S.charAt(left))){
                        	if(set.contains(S.charAt(left))){
                        		set.remove(S.charAt(left));
                        	}
                        }
                    }
                    /*
                    while(count==T.length()){//now cover all characters in T
                        if(minlen>i-left+1){ // record the minimum length and the new minleft, later we need it to return the substring
                            minlen = i-left+1;
                            minleft = left;
                        }
                        if(map.containsKey(S.charAt(left))){// if the left most character in S is also in T& I0 k' ^) @0 l
                            map.put( S.charAt(left), map.get(S.charAt(left))+1 );// before move to right, add one to the mapped value(means removed a redundant character if the value still samller than 0 or removed a covered character if the value become bigger than 0)
                            if(map.get(S.charAt(left))>0) { // if the mapped value become bigger than 0, means a covered character is removed- F' p9 c, u( t: ]+ `
                                count--; 
                            }
                        }
                        left++;
                    }
                    
                    */
                }
            }
        }
        if(minlen==S.length()+1){
        	return ""; 
        }
        return S.substring(minleft,minleft+minlen);
    }
    private class Multiset<T>{
    	private Map<T, Integer> map = new HashMap<T, Integer>();
    	boolean contains(T item){
    		return map.keySet().contains(item);
    	}
    	void add(T item){
    		if(!contains(item)){
    			map.put(item, 1);
    		}else{
    			map.put(item, map.get(item) + 1);
    		}
    	}
    	
    	void remove(T item){
    		assert(contains(item));
    		Integer count = map.get(item);
    		if(count==1){
    			map.remove(item);
    		}else{
    			map.put(item, count - 1);
    		}
    	}
    	boolean isEmpty(){
    		return map.isEmpty();
    	}
    }
}
