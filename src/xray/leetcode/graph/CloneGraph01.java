package xray.leetcode.graph;

import java.util.*;

/*
 * IDEA use a map to save the mapping of old node and new node
 * 
 * IDEA also use the map to know which one is visited
 * 
 * TIP: like DP check cache before processing, return cache if visited 
 * TIP: do not check on neighbor, as the cache should have taken care of that.  
 *   
 */
public class CloneGraph01 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null){
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return cloneGraph(map, node);
    }
    
    private UndirectedGraphNode cloneGraph(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node){
        if(node==null){
            return null;
        }
        UndirectedGraphNode newNode = map.get(node);
        if(newNode!=null){
        	return newNode;
        }
        newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        
        if(node.neighbors!=null){
            for(UndirectedGraphNode neighbor : node.neighbors){
            	UndirectedGraphNode newNeighbor = cloneGraph(map, neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        return newNode;
    }
}
