package xray.leetcode.string;
import java.util.*;
public class SubstringwithConcatenationofAllWords {
	public static void main(String[] args) {
		SubstringwithConcatenationofAllWords s = new SubstringwithConcatenationofAllWords();
		
		List<Integer> x = s.findSubstring("aaa", new String[]{"a","b"});
		return;
	}

	/*
	 * !!READ the problem
	 * 
	 * 1. All sub strings must be used to count as a match!
	 * 2. each index could be a start even if it was in the middle of a word
	 *  
	 * TIP use a count map for the main string, comparing its count to the dict.
	 * Do not create one for dict and remove from it.  Reconstructing it will need too much time. 
	 * 
	 * 
	 */
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        if(S==null||L==null){
            return res;
        }
        int lens = S.length();
        int size = L.length;
        if(size==0){
            return res;
        }
        int k = L[0].length();
        if(k==0){
            return res;
        }
        
        int totalLen = k*size;
        
        if(lens<totalLen){
            return res;
        }
        
        Map<String, Integer> dict = new HashMap<String, Integer>();
        for(String w : L){
            addMap(dict, w);
        }

        for(int i=0;i<=lens-totalLen;i++){ //Needs to scan for each one; one optimization is to ignore the last few due to length,     
        	/* totalLen = 2   
        	 * lens - 2  , lens - 1, (lens)
        	 *    2           1
        	 *    lens - totalLen is the max possible start
        	 *    
        	 */
            if(matches(S, i, dict, k, size)){
                res.add(i);
            }
        }
        return res;
    }

    private boolean matches(String S, int start, Map<String, Integer> dict, int k, int size){
        Map<String, Integer> smap = new HashMap<String, Integer>();  
        int i=start;
        int matchCount = size;
        while(matchCount>0){ //when in this loop, either a totally matching substring, or failed one, no intermediate ones
            String word = S.substring(i, i+k);
            if(dict.containsKey(word)){  
                int counts = addMap(smap, word);
                int countd = dict.get(word);
                if(counts>countd){
                    break;
                }
                i+=k;  //LESSON: only do the successful processing when it is really the successful branch. OR even if the branching doesn't matter, the value may be wrong.  
                matchCount--;
            }else{
                break;
            }
        }
        return matchCount==0;
    }

    private int addMap(Map<String, Integer> map, String key){ //TIP this is short, unless we want an object, say set or list as value
        if(map.containsKey(key)){
            int count = map.get(key)+1;
            map.put(key, count);
            return count;
        }else{
            map.put(key, 1);
            return 1;
        }
    }
}	
