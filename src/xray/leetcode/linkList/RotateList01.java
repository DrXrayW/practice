package xray.leetcode.linkList;
/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
public class RotateList01 {
    public ListNode rotateRight(ListNode head, int n) {
        if( (head==null)||(n<0) ){
            return null; 
        }
        
        if(n==0){
            return head;
        }
        
        //do not need the dummy head as we have a looping trick here
        
        //getting the length so that we only need to move n % length times
        int length = 0;
        ListNode tail = null; 
        for(ListNode node=head;node!=null;node=node.next){
            tail = node; //also save the tail
            length++;
        }
        int k = n % length;

        //form a loop
        tail.next = head; 
        
        /*We want link operation, so we need to point at tail, not head
         * as the tail is the pre node of head when there is a loop
         * 
         */
        ListNode pre = tail; //Note, this is rotate right!, so move (length - k ) times
        for(int i=0;i<(length-k);i++){
            pre=pre.next;
        }
        
        //break the loop after pre
        ListNode newHead = pre.next; 
        pre.next = null; 
        
        return newHead;
    }
}	
