package xray.leetcode.linkList;

/*
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

 */
public class IntersectionOfTwoLinkedList01 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if( (headA==null)||(headB==null) ){
            return null;
        }
        
        //find the count of two link lists, also their last node
        int counta = 0;
        ListNode na = headA;
        while(na.next!=null){
            counta ++;
            na=na.next;
        }
        
        int countb = 0;
        ListNode nb = headB;
        while(nb.next!=null){
            countb ++;
            nb=nb.next;
        }
        
        //if the last node doesn't match, they do not intersect
        if(na != nb){
            return null; 
        }
        
        na = headA;
        nb = headB;
        
        //cut the extra nodes in the longer one
        if(counta > countb){
            for(int i=0;i< (counta - countb );i++ ){
                na = na.next;
            }
        }
        
        if(countb > counta){
            for(int i=0;i< (countb - counta );i++ ){
                nb = nb.next;
            }
        }
        
        //now they must meet at the same position, so compare one by one
        while(na!=nb){
            na=na.next;
            nb=nb.next;
        }

        return na;        
    }
}
