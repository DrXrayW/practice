package xray.leetcode.linkList;

public class InsertionSortList {
	//this is the wrong version
    public ListNode insertionSortList(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode d = new ListNode(Integer.MIN_VALUE);
        d.next = head;
        
        for(ListNode mainpre = d.next;mainpre.next!=null;){
        	ListNode node = mainpre.next;
            int value = node.val;
            ListNode savedNext = node.next;
            for(ListNode pre = d;pre.next!=null;pre=pre.next){
                if(pre.next.val > value){
                    //insert
                    ListNode tmp = pre.next;
                    pre.next = node;
                    node.next = tmp;
                    
                    break;
                }
            }
            //for main loop
            mainpre.next = savedNext;
        }

        return d.next;
    }
}
