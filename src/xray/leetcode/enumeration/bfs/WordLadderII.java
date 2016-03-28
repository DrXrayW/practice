package xray.leetcode.enumeration.bfs;

import java.util.*;

/*
 * This is actually Dijkstra
 */
public class WordLadderII {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(start==null||end==null||dict==null){
            return res;
        }
        if(!dict.contains(start)||!dict.contains(end)){
            return res;
        }
        
        //a map to keep track of visiting and save shortest and paths information in
        Map<String, Node> paths = new HashMap<String, Node>();
        
        //queue for BFS
        Queue<String> queue = new LinkedList<String>();

        //Initialize by putting the first node
        //len starts at 1 from the first word, this is set when the node is put in the queue
        Node firstNode = new Node();
        List<String> firstPath = new ArrayList<String>();
        firstPath.add(start);
        firstNode.addPath(firstPath);
        
        paths.put(start, firstNode);
        queue.add(start);
        
        //minlen keeping track of the known minlen, null if unknown 
        Integer minlen = null;

        while(!queue.isEmpty()){
        	//While visiting a node from queue.remove
            String s = queue.remove();
            Node node  = paths.get(s);
            //Check whether we have reached the end!!
            if(s.equals(end)){
                minlen = node.len; //If reached: we cannot stop as we want all path, but note the min length down
            }
            if(minlen!=null&& node.len >= minlen){ //If we already know the min length, stop expanding
                continue; 
            }
            for(int i=0;i<s.length();i++){ //expand to the next
                for(char c = 'a'; c<='z'; c++){
                    StringBuilder next = new StringBuilder(s);
                    next.setCharAt(i, c);
                    String nextStr = next.toString();
                    if(s.equals(nextStr)||!dict.contains(nextStr)){  //Need to filter on the next step, 1. cannot be selfloop, 2. dict needs to have it!
                        continue;
                    }
                    Node nextNode = paths.get(nextStr);  //access cache map
                    if(nextNode==null){ //not visited 
                        nextNode = new Node();		//create new node
                        paths.put(nextStr, nextNode); //put in map
                        queue.add(nextStr);           //add to expand
                    }       
                    for(List<String> curpath : node.paths){  //visited or not, update its paths
                        List<String> newpath = new ArrayList<String>(curpath);
                        newpath.add(nextStr);    
                        nextNode.addPath(newpath);
                    }
                }
            }
        }
        
        if(paths.get(end)!=null){
            res.addAll(paths.get(end).paths);
        }
        return res;
    }
    
    private class Node{ 
    	Set<List<String>> paths = new HashSet<List<String>>();
    	int len = Integer.MAX_VALUE; //auto maintained while new path passed in
    	
    	void addPath(List<String> newPath){  //TIP do not add a longer one, a shorter one will kill all existing ones, add a equal or shorter one
    		int newStep = newPath.size();
    		if(newStep > len){
    			return;
    		}
    		List<String> clone = new ArrayList<String>(newPath);
    		if(newStep < len){
    			paths.clear();
    			len = newStep;
    		}
    		paths.add(clone);
    	}
    	
    }
}	
