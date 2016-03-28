package xray.leetcode.linkList;
import java.util.*;

/*
 * The IDEA is to merge the lists using merge two lists one by one. 
 * The trick is that always merging to one is not good, but merging in pairs is better. 
 * 
 *  However, there is a better one using min heaps
 *  
 *  merge happens at all iterations
 *  
 *  k/2 + 4/k + ... + 1
 *  
 *  size of the lists 
 *  
 *  n + 2n + ... + 2^(logk-1)
 *  
 *  
 *  so that is 
 *  k/2*n + 4/k*2n + ...
 *  
 *  k/2*n + ... + k/2*n
 *  
 *  we know that there are log k iterations
 *  
 *  k/2 * n * logk
 *  so O(nklogk)
 *
 *
 * why merging them one by one is bad?
 * 
 * so we need k - 1 merges, the length is
 *  
 * n + 2n + 3n + ...+ (k-1)n
 * 
 * that is O(n k^2)
 *
 *  O(nk^2) runtime, O(1) space
 */
public class MergKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        if((lists==null)||(lists.isEmpty())){
            return null;
        }
        
        int size = lists.size();
        if(size==1){
            return lists.get(0);
        }
        List<ListNode> newLists = new ArrayList<ListNode>();
        for(int i=0;i<size;i=i+2){
            if(i<size - 1) {
                newLists.add(mergeTwoLists( lists.get(i), lists.get(i+1) ) );
            }else{
                newLists.add(lists.get(i));
            }
        }
        return mergeKLists(newLists);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
