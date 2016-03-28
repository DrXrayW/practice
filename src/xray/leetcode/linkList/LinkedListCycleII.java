package xray.leetcode.linkList;

public class LinkedListCycleII {
	/*
	 * a = length before cycle
	 * b = length after cycle
	 * r = length of cycle
	 * 
	 * so 
	 * fast: a + b + nr
	 * slow: a + b + mr
	 * 
	 *  fast = 2 slow
	 *  
	 *  a + b + nr = 2a + 2b + 2mr
	 *  a + b = (n-2m)r
	 *  
	 * that means from b in the cycle, moving a times, it will reach the start of cycle
	 * 
	 * therefore, if one starts from the meet, one starts from head, when they meet, it is the start of cycle
	 * 
	 */
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while(fast!=null&&fast.next!=null){ //same as cycle detection, find the meet
            fast=fast.next.next;
            slow = slow.next;
            if(fast==slow){
                hasCycle = true;
                break;
            }
        }
        
        if(!hasCycle){ //need to know whether we have a cycle
            return null; 
        }
        
        ListNode meet = fast;
        
        ListNode node1 = head;
        ListNode node2 = meet;
        while(node1!=node2){    //we have a cycle so no null check, when they meet, it is ok. 
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }
}
