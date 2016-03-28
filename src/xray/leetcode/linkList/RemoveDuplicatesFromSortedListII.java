package xray.leetcode.linkList;
/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return head;
        }
        
        ListNode d = new ListNode(0); //needs a dummy as the first one may be gone
        d.next = head;
        
        ListNode pre = d; //pre's next is always the unique value to test
        while(pre.next!=null){
            ListNode cur = pre; //start from the same point
            while((cur.next!=null)&&(pre.next.val==cur.next.val)){ //look for same values, since they starts at the same point, always one match
                cur=cur.next;  //we use cur.next in this look ahead loop because we need to save the node BEFORE the first different node or null
            }
            if(cur==pre.next){  //only moved once, unique, both move one step forward
                pre = pre.next; 
                cur = cur.next;
            }else{
                pre.next = cur.next; //keeping pre, but short cut from pre.next to cur, 
                //pre unchanged
            }
        }
        return d.next;
    }
}
