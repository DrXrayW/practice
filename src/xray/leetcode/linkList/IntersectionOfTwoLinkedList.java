package xray.leetcode.linkList;

public class IntersectionOfTwoLinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if( (headA==null)||(headB==null) ){
            return null;
        }
        
        int counta = 0;
        ListNode na = headA;
        ListNode lasta = headA;
        while(na!=null){
            lasta = na;
            counta ++;
            na=na.next;
        }
        
        int countb = 0;
        ListNode nb = headB;
        ListNode lastb = headB;
        while(nb!=null){
            lastb = nb;
            countb ++;
            nb=nb.next;
        }
        
        if(lasta != lastb){
            return null; 
        }
        
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;

        if(counta > countb){
            for(int i=0;i< (counta - countb );i++ ){
                newHeadA = newHeadA.next;
            }
        }
        
        if(countb > counta){
            for(int i=0;i< (countb - counta );i++ ){
                newHeadB = newHeadB.next;
            }
        }
        
        na = newHeadA;
        nb = newHeadB;
        while(na!=null){
            if(na==nb){
                return na;
            }
            na=na.next;
            nb=nb.next;
        }

        return null;        
    }
}
