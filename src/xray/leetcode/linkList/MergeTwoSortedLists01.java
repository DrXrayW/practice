package xray.leetcode.linkList;

/*
 * create a new dummy head, add the one smaller.
 * remember to add the remaining ones when the other one is empty
 * 
 * TIP: usually when a node is taking out to a new one, its next should be set to null,
 * however, since it will be the tail, the next will be updated if there is more,
 * or it is already null, so there is no need here.
 */
public class MergeTwoSortedLists01 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);//TIP a dummy head
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
        
        d.next =  (i1!=null) ? i1 : i2;  //TIP connect to the unfinished linklist
        
        return h.next;
    }
}
