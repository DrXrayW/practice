package xray.leetcode.string;
import java.util.*;
/*
 * return all words with anagram peer
 * 
 * IN SHORT: use sorted strings as keys to a map
 * 
 * IDEA use the sorted string (using Arrays.sort on s.toCharArray then create String on the char array) as key
 * put all strings in the same group.
 * output any group with size >2
 */
public class Anagrams {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs==null||strs.length==0){
            return res;
        }
        //use a map to hold same anagrams
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s : strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.get(key);
            if(list==null){
                list = new ArrayList<String>();
                map.put(key, list);
            }
            list.add(s);
        }
        for(List<String> list : map.values()){
            if(list.size()>1){
                res.addAll(list); //add to result if there are more than one in a group, note that we do not want the groups separated
            }
        }
        return res;
    }
}
