package xray.leetcode.linkList;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head==null){
            return false;
        }
        //no need to have a dummy head, as there is no modification of links
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next!=null){  //really doesn't matter, as long as this offers enough NPE protection
            fast = fast.next.next;  //the two step pointer will get it anyways. 
            slow = slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
}
