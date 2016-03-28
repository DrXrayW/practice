package xray.leetcode.linkList;
/*
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

do not need dummy as the first one is always safe
 */
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return null;  //ask about this
        }
        ListNode node = head;
        while(node!=null){
            ListNode s = node.next;
            while( (s!=null)&&(s.val==node.val) ){ //look ahead, find the first one either null or another value
                s=s.next;
            } 
            node.next = s;  //then link it to the current node
            node = node.next;
        }
        return head;
    }
}
