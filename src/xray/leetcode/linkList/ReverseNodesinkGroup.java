package xray.leetcode.linkList;

/*
 * 
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

 *
 */
public class ReverseNodesinkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if( (head==null)||(k<1) ){
            return null;
        }
        if(k==1){
            return head;
        }
        
        ListNode d = new ListNode(0);
        d.next = head;
        
        /*
                  +---------------------v                     
         d ->     1  -X->     2  -X->   3  -X-> 4 -> 5 -> NULL
     [before]   [tail]  <-  [cur]
        +---------------------^
        */
        
        ListNode pre = d;
        while(pre.next!=null){
            boolean enough = true;
            ListNode check = pre;
            for(int i=0;i<k;i++){ //check whether enough for k, as we are not doing anything otherwise
                check=check.next;  
                if(check==null){
                    enough = false;
                    break;
                }
            }
            
            if(!enough){
            	break;
            }
            
            ListNode tail = pre.next;
            ListNode cur = tail.next;
            if(cur==null){
                break;
            }
   
            for(int i=0;i<k-1;i++){ //looping k-1 times, as the reverse starts at 2, the real condition is i<k-1, also cur!=null
                if(cur==null){ //we do not need to check if have k checked, this is for we still need to reverse the last part even not enough
                    break;    
                }
                tail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                cur = tail.next; //iteration
            }
            
            pre = tail; //remember to update for next group!!
        }
        
        return d.next;
    }
}
