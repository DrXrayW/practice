package xray.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class MinimumWindowSubstring01 {
	public String minWindow(String S, String T) {
	      String res = "";
	      if(S == null || T == null || S.length()==0 || T.length()==0){
	          return res;
	      }
	      
	      HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
	      for(int i =0;i < T.length(); i++){
	          if(!dict.containsKey(T.charAt(i))){
	              dict.put(T.charAt(i), 1);
	          }
	          else{
	             dict.put(T.charAt(i), dict.get(T.charAt(i))+1);
	          }
	     }
	     
	     int count = 0;
	     int pre = 0;
	     int minLen = S.length()+1;
	     
	     for(int i=0;i<S.length();i++){
	         
	         if(dict.containsKey(S.charAt(i))){
	             dict.put(S.charAt(i),dict.get(S.charAt(i))-1); //reduce 1 anyways
	             if(dict.get(S.charAt(i)) >= 0){ //when there was at least one left, count as a useful remove
	                 count++;
	             }
	             while(count == T.length()){ //when all are covered
	                 if(dict.containsKey(S.charAt(pre))){ //look at the head of the current string
	                     dict.put(S.charAt(pre),dict.get(S.charAt(pre))+1); //add it back to our todo list
	                     
	                     if(dict.get(S.charAt(pre))>0){  //only when it is greater than 0 it is a necessary(min) one
	                         if(minLen>i-pre+1){        //update min length
	                             res = S.substring(pre,i+1);
	                             minLen = i-pre+1;
	                         }
	                         count--; //add a useful one back, so mark one less found
	                     }
	                 }
	                 pre++;
	             }
	         }
	     }
	     return res;
	 }
}
