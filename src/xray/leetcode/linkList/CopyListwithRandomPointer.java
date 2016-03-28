package xray.leetcode.linkList;

import java.util.*;

public class CopyListwithRandomPointer {
	/*
	 * the hard point is to find the new created node for the new list
	 * use a hashmap to lookup new node for the old node
	 * 
	 *  see tips below
	 */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){
            return null;
        }
        
        Map<RandomListNode, RandomListNode> lookup = new HashMap<RandomListNode, RandomListNode>();
        
        RandomListNode newHead = new RandomListNode(0);    //we do need a new head, create a dummy here
        RandomListNode tail = newHead;						//we also need to know where we are in the new list, to connect new nodes to
        
        for(RandomListNode node=head;node!=null;node=node.next){  //looping through the old list
            RandomListNode newNode = getOrCreate(node, lookup);   
            tail.next = newNode;										//add to the list!  do not forget this
            if(node.next!=null){
                newNode.next = getOrCreate(node.next, lookup);
            }
            if(node.random!=null){
                newNode.random = getOrCreate(node.random, lookup);
            }
            tail = newNode;											//update the new tail
        }
        return newHead.next;
    }
    
    private RandomListNode getOrCreate(RandomListNode node, Map<RandomListNode, RandomListNode> lookup){
        RandomListNode newNode = lookup.get(node);
        if(newNode==null){
            newNode = new RandomListNode(node.label);
            lookup.put(node, newNode);
        }
        return newNode;
    }
}
