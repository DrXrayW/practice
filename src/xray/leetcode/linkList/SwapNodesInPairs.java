package xray.leetcode.linkList;


public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode d = new ListNode(0);
        d.next = head;
        
        ListNode pre1 = d;
        while(pre1.next!=null){
            ListNode pre2 = pre1.next;
            if(pre2.next==null){
                break; //odd, no swapping here
            }
            //swap pre1.next and pre2.next
            
            /*
            +----------v      
            d     1 <- 2   3 -> 4 -> NULL
                  +--------^     
            pre1  node1 
                  pre2   node2
                  
            */
            
            ListNode node1 = pre1.next;
            ListNode node2 = pre2.next;
            ListNode next = node2.next;
            
            pre1.next = node2;
            node2.next = node1;
            node1.next = next;
            
            pre1 = node1;
        }
        
        return d.next;
    }
}
