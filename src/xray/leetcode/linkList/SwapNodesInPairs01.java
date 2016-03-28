package xray.leetcode.linkList;

/*
 * Same as the reverse link list
 * Here we do it pairwise, when any of the pair is null, then bail out
 */
public class SwapNodesInPairs01 {
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode d = new ListNode(0);
        d.next = head;
        
        ListNode pre = d;
        ListNode p = pre.next;
        while(p!=null&&p.next!=null){
            //swap pre1.next and pre2.next
            
            /*
            +----------v      
            d     1 <- 2   3 -> 4 -> NULL
                  +--------^     
            pre   p    q   q.next 
                       
                  
            */
        	ListNode q = p.next;
        	p.next = q.next;
        	q.next = p; //this can be	q.next = pre.next; as in the reverse case, but since we are not reversing more, this is good for this  
        	pre.next = q;
        	
        	//remember to move forward
        	pre = p;   //also we have done with reversing, so the pre needs to be updated too!! before p updates as it is taking p.   
        	p = p.next;  
        }
        
        return d.next;
    }
}
