package xray.leetcode.enumeration.bfs;

import java.util.*;

public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        Map<String, Integer> visited = new HashMap<String, Integer>();
        Queue<String> toGo = new LinkedList<String>();

        toGo.add(start);
        visited.put(start, 1); //the trick is that we needs to remember the history of a to go node, here only step matters, so this
        
        while(!toGo.isEmpty()){
            String str = toGo.remove();
            int step = visited.get(str);
            if(str.equals(end)){
                return step;
            }
            
            //expand
            for(int i=0;i<str.length();i++){
                for(char c = 'a'; c<='z'; c++){
                    StringBuilder n = new StringBuilder(str);
                    n.setCharAt(i, c);
                    String nstr = n.toString();
                    if(!visited.keySet().contains(nstr)){
                        if(dict.contains(nstr)){
                            toGo.add(nstr);
                            visited.put(nstr, step + 1);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}	
