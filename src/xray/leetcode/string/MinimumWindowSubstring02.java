package xray.leetcode.string;

import java.util.*;

/*
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.


IN SHORT:
1. counter map for current substring/window
	a count for useful chars in the window
2. when it is a char we care
	reduce the number, only when the number >=0, this one helps, so the count of useful chars ++
	When the count reaches the required number (T.length())
	 try to spit out the left, while left more along,  maintaining map and count using the same idea.

 */
public class MinimumWindowSubstring02 {
    public String minWindow(String S, String T) {
	      String res = "";
	      if(S == null || T == null || S.length()==0 || T.length()==0){  //discussion
	          return res; 
	      }
	      /*
	       * //TIP: count for chars, can go negative; 
	       * TIP: min sub string may have negatives, like abbbbbbbc, for abc,
	       */
	      Map<Character, Integer> map = new HashMap<Character, Integer>();     
	      for(int i =0;i < T.length(); i++){
	    	  char c = T.charAt(i);
	          if(!map.containsKey(c)){
	              map.put(c, 1);
	          }
	          else{
	             map.put(c, map.get(c)+1);
	          }
	     }
	     
	     int left = 0; //the left position of current substring
	     int count = 0; //count of useful chars in the current substring
	     int minlen = S.length() + 1; //keep track of min length
	     for(int i=0;i<S.length();i++){
	         char c = S.charAt(i);
	         if(map.containsKey(c)){ //only when it is one we care
	             map.put(c, map.get(c) - 1); //reduce 1 anyways
	             if(map.get(c) >=0){  //if after reduce still >= 0, that means the reduce helped, count ++  
	                 count ++;
	             }
	             while(count==T.length()){  //when we have enough count, i.e. all chars covered, 
	                 char leftc = S.charAt(left); 
	                 if(map.containsKey(leftc)){ //if the left char is one that matters (not mattered ones should just be removed by left++) 
	  	                 map.put(leftc, map.get(leftc) + 1); //put the char back to map (remove from our substring) 
	  	                 if(map.get(leftc) > 0){  // if putting the char back will make the number > 0, i.e. not enough again, the one is necessary
	  	                    if(minlen>i-left+1){ //then we found a local minimum
	  	                        minlen = i-left+1;  //update left .... i, including left and i, so + 1
	  	                        res = S.substring(left, i+1); //the substring end is excluded, so i+1
	  	                    }
	  	                    count --; //the one is necessary, removing it then we need another count; note that other chars won't replace this char, as they are 0 in the map right now
	  	                 }
	                 }
	                 left ++;
	             }
	         }
	     }
         //always find the min when we have a satisfying window, so no need to finalize after the loop
	     return res;
  }
}
