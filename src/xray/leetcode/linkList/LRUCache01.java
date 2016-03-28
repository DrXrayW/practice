package xray.leetcode.linkList;

import java.util.HashMap;
import java.util.Map;

/*
 * this skips map operation if not needed
 */
public class LRUCache01 {
	//double link list as node
    class CacheNode{
    	//add getter and setter
        int key;
        int val;
        CacheNode pre = null;
        CacheNode next = null;
        
        CacheNode(int key, int value){
            this.key = key;
            this.val = value;
        }        
    }

    private final int capacity;
    private int size = 0; 
    private final Map<Integer, CacheNode> lookup = new HashMap<Integer, CacheNode>();
    //tip 1: use guard for both head and tail to avoid trouble
    private final CacheNode cacheHead = new CacheNode(0, 0); //most recent
    private CacheNode cacheTail = new CacheNode(0,0); //least recent
    
    public LRUCache01(int capacity) {  //for leetcode, remember to omit the 01
        //error handling capacity if needed
        this.capacity = capacity;
        cacheHead.next = cacheTail;
        cacheTail.pre = cacheHead;
    }
    
    public int get(int key) {
        CacheNode node = lookup.get(key);
        if(node == null){
            return -1;
        }
        refresh(key, node);
        return node.val;
    }
    
    private void refresh(int key, CacheNode node) {
    	remove(node, false);         //remove the found one and put it in the most recent place, head
		putInHead(key, node, false);
	}

	public void set(int key, int value) {
        CacheNode node = lookup.get(key);
        if(node==null){
            if(size == capacity){
                remove(cacheTail.pre, true);
            }else{
                size ++;		//tip: only for a new node we need to update size
            }
            CacheNode newnode = new CacheNode(key, value);
            putInHead(key, newnode, true);
        }else{
            //existing key, refresh value and position
            node.val = value; //tip: do not forget to update the value even if it is already there
            refresh(key, node);
        }
    }
    
    private void putInHead(int key, CacheNode node, boolean mapOp){
    	if(mapOp){
    		lookup.put(key, node); //remember to maintain the hash table
    	}
        CacheNode next = cacheHead.next;
        cacheHead.next = node;
        node.pre = cacheHead;
        next.pre = node;
        node.next = next;
    }
    
    private void remove(CacheNode node, boolean mapOp){
        if(node==null){
            return;
        }
        CacheNode pre = node.pre;
        CacheNode next = node.next;
        pre.next = next;
        next.pre = pre;
    
        node.pre = null;
        node.next = null;
        
        if(mapOp){
        	lookup.remove(node.key); //remember to maintain the hash table
        }
    }
}
