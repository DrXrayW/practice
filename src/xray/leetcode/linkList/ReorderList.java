package xray.leetcode.linkList;
/*
 * You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if(head==null){
            return;
        }
        
        //find middle
        ListNode fast = head;
        ListNode slow = head;
        /*
        study the middle here
        F: fast
        S: slow
        1
        [1]
        F
        S
        
        2
        [1] [2]
         F
         S
        
        3
        [1][2][3] 
               F
            S
            
        4
        [1][2][3][4]
               F
            S
            
        5
        [1][2][3][4][5]
                     F
               S     
               
        so, for 1 and 2, S at 1
        odds: S: the middle
        even: S: the one before middle
        
        */
        while((fast.next!=null)&&(fast.next.next!=null)){
            fast = fast.next.next;
            slow = slow.next;
        }
        if(slow==head){
            return;
        }
        //slow is the middle. 
        
        //based on the middle analysis, we need to reverse what is after the middle/slow, then merge with the left part
        ListNode mid = slow;
        ListNode rightHead = new ListNode(0);
        rightHead.next = mid.next;
        // in this kind of break and merge, it is very important to break the link!!!
        mid.next = null; 
        
        
        //reverse the second part
        //remember reverse link list trick!!!!
        ListNode pre = rightHead;
        ListNode tail = rightHead.next;
        if(tail!=null){
            ListNode cur = tail.next;
            while(cur!=null){
                tail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                cur = tail.next;
            }
        }
        //merge the two. 
        ListNode right = rightHead.next;
        ListNode left = head; //because we do not change the head of left, so no need to create dummy for left
        while(right!=null){ //only check right as right is equal or shorter 
            ListNode leftNext = left.next;
            ListNode rightNext = right.next;
            //connect the two links
            left.next = right;
            right.next = leftNext;
            //update iterater
            left = leftNext;
            right = rightNext;
        }
    }
}
