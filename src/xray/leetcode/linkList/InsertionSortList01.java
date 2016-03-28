package xray.leetcode.linkList;
/*
 * Sort a linked list using insertion sort.
 * 
 * idea, for each node
 * 
 *      
 *     |<  sorted  >|   insert this new node in the sorted list 
 * [D]->(2)->(3)->(6)->(5)->...
 * 
 */
public class InsertionSortList01 {
    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return null; 
        }
        
        ListNode d = new ListNode(0);
        
        for(ListNode node=head;node!=null;){ //note here is head, not d, so d's value doesn't matter
            ListNode next = node.next; //save next
            
            /*
             * find pre.next.val > node, then insert before it, since we start from d.next that is all, 
             * note that we do not need to mark the right bound, 
             * the current node's value must be equal to itself, also, if there are more nodes of the same value, we will insert at the end of it  
             */
            ListNode pre = d; 
            while( (pre.next!=null)&&(pre.next.val<=node.val) ) {
                pre = pre.next;
            }
            ListNode tmp = pre.next;
            pre.next = node;
            node.next = tmp;
            
            node=next;
        }   
        
        
        return d.next;
    }
}
