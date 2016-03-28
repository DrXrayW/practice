package xray.leetcode.sort;

import xray.leetcode.linkList.ListNode;
/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 * merge sort
 * 
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if( (head==null)||(head.next==null) ) {
            return head;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        while( (fast.next!=null)&&(fast.next.next!=null) ) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null; //break the list
        
        return mergeTwoLists(sortList(l1), sortList(l2));    
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(-1);
        ListNode d = h;
        ListNode i1 = l1;
        ListNode i2 = l2;
        while( (i1!=null)&&(i2!=null) ){
            if(i1.val<i2.val){
                d.next = i1;
                i1=i1.next;
            }else{
                d.next=i2;
                i2=i2.next;
            }
            d = d.next;
        }
        
        d.next =  (i1!=null) ? i1 : i2;
        
        return h.next;
    }
}
