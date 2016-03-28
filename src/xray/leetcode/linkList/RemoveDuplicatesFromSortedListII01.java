package xray.leetcode.linkList;
/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

 */
public class RemoveDuplicatesFromSortedListII01 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return head;
        }
        
        ListNode d = new ListNode(0); //needs a dummy as the first one may be gone
        d.next = head;
        
        ListNode pre = d; 
        while(pre.next!=null){ //note it is pre.next not null, as cur is null, but we need pre for linklist operations
        	ListNode cur = pre.next; //cur, pre's next, is always the value under unique test
            ListNode dup = cur; //start from the same point!!!, to be able to tell whether moved or not
            while((dup.next!=null)&&(cur.val==dup.next.val)){ //dup only moves when its next is !=null and having the same value as the cur 
            	dup=dup.next;  
            }
            if(dup==cur){  //dup is cur, not a dup,  
                pre = pre.next;  //saving pre
            }else{
                pre.next = dup.next; //discard cur, which is pre.next, until dup.next, which we know is a new value or null 
            }
        }
        return d.next;
    }
}
