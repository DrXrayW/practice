package xray.leetcode.linkList;


/*
 * This is bad, this tries to merge l2 to l1, but there is no need, 
 * as adding the linked list to a new one is also "in place", 
 * 
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode d1 = new ListNode(0);
        d1.next = l1;
        
        ListNode d2 = new ListNode(0);
        d2.next = l2;
        
        ListNode p1 = d1;
        ListNode i1 = d1.next;
        
        ListNode p2 = d2;
        ListNode i2 = d2.next;
        
        while( (i1!=null)&&(i2!=null) ){
            if(i2.val<i1.val){
                //remove from L2   
                p2.next = i2.next;  
                
                //add to L1
                p1.next = i2; 
                i2.next = i1;
                
                
                //update tmp pointers
                i2 = p2.next;
                p1=i2;
                
            }else{
                p1=p1.next;
                i1=i1.next;
            }
        }
        
        if(i2!=null){ //this is a direct link, do not use while
            p1.next = i2;
        }
        
        return d1.next;
    }
}
