package xray.leetcode.linkList;
/*
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head==null){
            return null;
        }
        
        //put nodes to two new lists, and connect them
        ListNode leftHead = new ListNode(0);
        ListNode left = leftHead;
        ListNode rightHead = new ListNode(0);
        ListNode right = rightHead;
        
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = null; //remember, when it is taken out, null the next
            if(cur.val<x){
                left.next = cur;
                left = cur;
            }else{
                right.next = cur;
                right = cur;
            }
            cur = next;
        }
        
        left.next = rightHead.next; //left is the left tail, or the leftHead itself, so linking to right would be fine 

        return leftHead.next;
    }
}
