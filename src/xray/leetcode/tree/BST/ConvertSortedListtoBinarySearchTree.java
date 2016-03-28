package xray.leetcode.tree.BST;

/*
 * TIP: bottom up, building the tree like the inorder traverse
 * TRICK: as the inorder goes, we are actually going through the link list one by one whenever we create a new node
 * TIP: needs to get the left and right of the range to correctly get the mid, so need the length
 * TIP: inorder so that:  left, now, then right
 * 
 * O(n) runtime, O(log n)
 * 
 */

import util.Ref;
import xray.leetcode.linkList.ListNode;
import xray.leetcode.tree.TreeNode;

public class ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null){
            return null;
        }
        
        int length = 0; //needs to know the length to decide where we are in the tree
        for(ListNode node = head; node!=null; node=node.next){
            length ++;
        }
        Ref<ListNode> cur = new Ref<>(head); 
        return sortedListToBST(0, length - 1, cur);
    }
    
  //TIP normal inclusion index handling
    private TreeNode sortedListToBST(int left, int right, Ref<ListNode> cur){
        if(left>right){
            return null;
        }
        int mid = (left + right) / 2;  
        TreeNode lefttree = sortedListToBST(left, mid - 1, cur);
        
        TreeNode node = new TreeNode(cur.value.val);
        cur.value = cur.value.next;
        node.left = lefttree;
        
        node.right = sortedListToBST(mid+1, right, cur);
        return node;
    }
}
