package xray.leetcode.linkList;
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return null;
        }
        
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode result = new ListNode(0); //dummy head: trick 1
        ListNode pre = result;
        int carry = 0;
        //using 'or' instead of 'and' to avoid treating the remained ones: trick 2  
        //trick 3: carry needs to be out side, as it should be passed around the loop, also used for terminating the loop
        while( (t1!=null)||(t2!=null)||(carry!=0) ){  
        	 
            int val1 = (t1!=null) ? t1.val : 0;
            int val2 = (t2!=null) ? t2.val : 0;
            int value = val1 + val2 + carry;  //Note that the real digit cannot be this value!!
            carry = value / 10;
            value = value % 10;
            if(t1!=null){
                t1=t1.next;
            }
            if(t2!=null){
                t2=t2.next;
            }
            ListNode newNode = new ListNode(value);
            pre.next = newNode;
            pre = newNode;
        }

        return result.next;
    }
}
