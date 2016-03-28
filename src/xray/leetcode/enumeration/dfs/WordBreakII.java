package xray.leetcode.enumeration.dfs;
import java.util.*;
/*
 * list all wordbreaks
 * 
 */
public class WordBreakII {
    public List<String> wordBreak(String s, Set<String> dict) {
        return wordBreak(s, dict, new HashMap<String, List<String>>() );  //TIP a hashmap as cache
    }
    
    private List<String> wordBreak(String s, Set<String> dict, Map<String, List<String>> cache){
        if(cache.containsKey(s)){
            return cache.get(s);
        }
        //NOTE that there is no recursion termination here. 
        
        List<String> res = new ArrayList<String>();
        for(String word : dict){
            if(s.startsWith(word)){ //find a valid token
                String right = s.substring(word.length());
                if(right.isEmpty()){ //if this is the last one, HERE is the termination.  
                    res.add(word);
                }else{
                    List<String> sub = wordBreak(s.substring(word.length()), dict, cache); //for solution type of dfs: the for loop is enough, because length = 0 is empty set
                    for(String sol : sub){  //either sub is length 0, or it comes with something valid.
                        res.add(word+" "+sol);
                    }
                }
            }
        }
        //when everything fails, i.e. no word can match at this position/s, res is empty 
        cache.put(s, res);
        return res;
    }
}
