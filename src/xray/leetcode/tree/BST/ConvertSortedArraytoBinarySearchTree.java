package xray.leetcode.tree.BST;

import xray.leetcode.tree.TreeNode;

/*
 * recursively create root on mid
 * connect the current root to the recursion result of left and right
 * 
 * O(n) runtime, O(log n) stack space
 * 
 */
public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) {
        if(num==null||num.length==0){
            return null;
        }
        
        return sortedArrayToBST(num, 0, num.length - 1); 
    }

    /*
     * using inclusive left and right
     */
    private TreeNode sortedArrayToBST(int[] num, int left, int right){
        if(left>right){ //TIP: still need to handle when left > right.  How about left == right?  In that case, mid will be one of them.  
            return null; //TIP: since we returned null for this, we do not neeed to return for the single node case
        }
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(num[mid]);
        
        node.left = sortedArrayToBST(num, left, mid - 1); //TIP: we are inclusive, and mid has been processed, mid - 1 
        node.right = sortedArrayToBST(num, mid + 1, right); //mid + 1
        
        return node;
    }
}
