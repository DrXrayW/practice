package xray.leetcode.linkList;
/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if( (head==null)||(n<0) ){ //corner case handling
            return null;
        }
        
        //using the dummy head for link list
        ListNode d = new ListNode(-1);
        d.next = head;
        
        //get length
        int length = 0;
        for(ListNode node=head;node!=null;node=node.next){
            length++;
        }
        
        //corner case
        if(length == 1){
            return head;
        }
        
        //get mode so that we are in a reasonable operation
        int k = n % length;
        if(k==0){
            return head;
        }
        
        //look ahead k times
        ListNode fast = d;
        for(int i=0;i<k;i++){
            fast = fast.next;
        }
        
        ListNode pre = d;
        
        while(fast.next!=null){
            fast = fast.next;
            pre = pre.next;
        }
        
        /*
         * say k = 2, length = 5 
         * [d] -> [1] -> [2] -> [3] -> [4] -> [5]
         * pre           fast
         * after more we want:
         *                      pre           fast
         * so we know that we move when fast.next!=null
         * 
         * now we construct the new list
         * 
         * pre.next is the new head
         * pre is the new tail, so pre.next should be set to null
         * 
         * fast is the old tail, it should be linked to the old head
         * which is d.next now.
         * 
         * the new head need to be set to d.next
         */
        
        //remove:
        ListNode newHead = pre.next;
        pre.next = null;
        
        ListNode oldHead = d.next;
        fast.next = oldHead;
        d.next = newHead;

        return d.next;
    }
}	
