package xray.leetcode.linkList;

public class RemoveNthNodeFromEndofList {
	//main trick: look ahead n, note that the current one is the pre, not the deleted node 
	//tip: a dummy head to avoid the mess of deleting the head itself
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode d = new ListNode(0);
        d.next = head;

        ListNode s = d;
        for(int i=0;i<n;i++){
            if(s.next!=null){
                s=s.next;
            }else{
                //if need to judge n
                //wrong n, do error handling
            }
        }
        //the previous step makes sure s is n hops/links ahead of pre
        /*
         * since we want to delete the nth from end. 
         * 
         * 0 -> 3 -> 2 ->1
         * 
         * moving together
         * if we want pre to stay at 2, then we want s to be null
         * but we want pre to be 3 for operation, so s->next == null is our condition 
         * 
         */
        ListNode pre = d;
        while(s.next!=null){
            s=s.next;
            pre=pre.next;
        }
        
        pre.next = pre.next.next;

        return d.next;
    }
}
