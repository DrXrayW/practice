package xray.leetcode.tree.BST;

import util.Ref;
import xray.leetcode.tree.TreeNode;

/*
 * 
 * TIP: 
 * 1 2 4 5 6 8 9
 * 
 * If two numbers got swapped, then 
 * 
 * 1 8 4 5 6 2 9
 * 
 * it means a small number is put in a large position, a large number is put in a small position
 * 
 * this means, 
 * 
 * the first one: if the previous node is > than the current node, then the previous one must be the large number put in a small area!!
 * 
 * The second one: similarly, but the current one may be it:
 * 	1. the current one is the next one of first, so it could be, or it may not be the swapped small one
 *  2. the current one is not the next of first, then it must be the second one
 *  
 *  So that is why when we find a violation, we note down the first, and the second. 
 *  but continue to scan to catch the real second, 
 *  We can see there is one catch: if the swapping is not guaranteed, we may use the wrong one as second, 
 *  However, we cannot tell that anyways. so the assumption must be true. 
 * 
 * A TIP TRICK here is to save pre node while traverse, so that we know exactly which prenode it is 
 *  
 * 
 */

public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
    	Ref<TreeNode> pre = new Ref<>(null);
    	Ref<TreeNode> first = new Ref<>(null);
    	Ref<TreeNode> second = new Ref<>(null);
        inOrder(root, pre, first, second);
        //if found, swap value
        if(first!=null&&second!=null){
            int tmp = first.value.val;
            first.value.val = second.value.val;
            second.value.val = tmp;
        }
    }
    private void inOrder(TreeNode root, Ref<TreeNode> pre, Ref<TreeNode> first, Ref<TreeNode> second){
        if(root==null){
            return;
        }
        inOrder(root.left, pre, first, second);
        
        if(pre!=null){
            if(pre.value.val>root.val){ //if violation, the first one must be the first, while the second one can be this, or the next one
                if(first==null){
                    first = pre;
                }
                second.value = root;    
            }
        }
        pre.value = root;
        
        inOrder(root.right, pre, first, second);
    }
}
