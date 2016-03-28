package xray.leetcode.linkList;

public class CopyListwithRandomPointer01 {
	/*
	 * the hard point is to find the new created node for the new list
	 * 
	 * no hashmap for this one to save spaces
	 * 
	 * but we need to modify the original one in the process, although we will revert our changes
	 * 
	 * 0. [0] -> [1] -> [2]
	 *
	 * 1. use next to chain the old and new up 
	 *    [0]   [1]   [2]
	 *     v  /  v   / v
	 *    [0']  [1']  [2'] 
	 * 
	 * 
	 * 2. since you can know the new node from the connected link list, update random pointer
	 * 
	 * 3. revert the changes. 
	 * 
	 */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){
            return null;
        }
        
        //1. link the two up, only next pointer is setup
        for(RandomListNode node=head;node!=null;){  //looping through the old list, do not use the for loop increaser as you are modifying the next pointer!!
            RandomListNode newNode = new RandomListNode(node.label);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }
        
        //2. setup random pointers in new list
        for(RandomListNode node=head;node!=null;){  //looping through the old list, do not use the for loop increaser as you are modifying the next pointer!!
        	RandomListNode newNode = node.next;
        	if(node.random!=null){ //random could be null!!!
        	    newNode.random = node.random.next; 
        	}
        	node = newNode.next;
        }
        
        //3. break the two link lists
        //we still need a head for the new guy
        RandomListNode newHead = head.next;
        
        for(RandomListNode node=head;node!=null;){  
        	RandomListNode newNode = node.next;
        	RandomListNode nextOldNode = newNode.next;
        	RandomListNode nextNewNode = null;
        	if(nextOldNode!=null){
        		nextNewNode = nextOldNode.next;
        	}
        	
        	//fix the links
        	node.next = nextOldNode;
        	newNode.next = nextNewNode;
        	
        	//update iterator
        	node = nextOldNode; 
        }
        
        return newHead;
    }
}
