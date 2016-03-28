package xray.leetcode.linkList;

/*
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null){
            return head;
        }
        
        ListNode d = new ListNode(-1); //dummy head trick very important for linkedlist
        d.next = head;
        
        /*
         * starting from dummy head, m steps further is the m-th element, 
         * but we want the pre, so m-1 
         */
        ListNode pre = d;
        for(int i=0;i<m-1;i++){
            pre = pre.next;
        }
        
        //pre is now the node before the reversed one
        
        /*
         m = 2
         n = 4
                         +----------------v                     
         d ->   1  -X->  2  -X->  3  -X-> 4 -> 5 -> NULL
             [pre]   [tail] <- [cur]
                +-----------------^
                
                cur is the current head of the reversed link list
                
                tail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                cur = tail.next;
               
                
        */
        
        ListNode tail = pre.next;
        ListNode cur = tail.next;
        for(int i=m;i<n;i++){ //from index n to m, n - m + 1 positions, then, n - m links to reverse, so i=m;i<n 
            tail.next = cur.next; //this goes first as tail.next is the same as tail.next is always cur, which is least important, so goes first
            cur.next = pre.next; //!! cannot be tail, as it needs to be the first one after head, pre.next is the prehead, cur needs to go before that
            pre.next = cur;
            cur = tail.next;
        }
        
        return d.next;
    }
}	
