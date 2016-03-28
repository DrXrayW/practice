package xray.leetcode.linkList;

/**
 * This is an alternation that for the non-multiple of k, still try to reverse it
 * @author xray
 *
 */
public class ReverseNodesinkGroupII {
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
        
        ListNode before = d;
        while(before.next!=null){
            ListNode tail = before.next;
            ListNode cur = tail.next;
            if(cur==null){
                break;
            }
            for(int i=0;i<k-1;i++){ //looping k-1 times, as the reverse starts at 2
                if(cur==null){
                    break;
                }
                tail.next = cur.next;
                cur.next = before.next;
                before.next = cur;
                cur = tail.next;
            }
            before = tail;
        }
        
        return d.next;
    }
}
