package xray.leetcode.graph;

import java.util.*;

/*
 * IDEA use a map to save the mapping of old node and new node
 * 
 * IDEA also use the map to know which one is visited
 * 
 * TIP: create node and put in map on Visit only, do not create new node on neighbor visiting, 
 * TIP: do check the visiting while expanding neighbor, only link but DO NOT recurse if visited 
 *   
 */
public class CloneGraph {
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
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        if(node.neighbors!=null){
            for(UndirectedGraphNode neighbor : node.neighbors){
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                if(newNeighbor==null){
                	newNeighbor = cloneGraph(map, neighbor);
                }
                newNode.neighbors.add(newNeighbor);
            }
        }
        return newNode;
    }
}
