package xray.leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import xray.leetcode.tree.TreeNode;
/*
 * 
 * [Same as inorder]
 * Time complexity: O(n)
 * 
 * Space complexity: O(1)
 * 
 * IDEA:
 * Using the prenode in traverse to save the current root. 
 * In this case, prenode is the right most node of the left subtree
 * 
 * Note that the restore happens when we visit the root, not the right most node of the left subtree!!
 * 
 * So, it goes like:
 * 		if left is null, then no need to save, just output the root and go right. 
 * 		if left not null
 * 			get the prenode, i.e. right most of the left subtree, 
 * 			since the prenode could point to the current node, so the termination condition would be:
 * 				prenode.right == null || prenode.right == node 
 * 			in while, then it become: 
 * 				prenode.right !=null && prenode.right != node
 * 			
 * 			So we have 2 situations
 * 			1. the prenode is not messed with yet. that means the left is not visited yet 
 * 			here we want to save the current node to the prenode's right, then go left (by updating node = node.left)				
 * 			
 * 			2. the prenode is pointing back to the current node.  Note that would never happens in a good tree, so must be a result of our trick. 
 *  		This means the prenode has been visited as the last of the left tree.  
 *  		So time to correct the result of the trick: pre.right = null.
 *  		Also as in inorder, when left tree is done, we output the current node 
 *  		Then go right. node = node.right. 
 * 
 * [adjustments]
 * 
 * The problem is that we can only output the root when both left and right subtrees are done.
 * 
 * if we save it using some stack, it would be O(n) space again. 
 * 
 * 1. Main trick:
 * 		Add a dummy node so that the whole tree is its left subtree
 * 		when a left tree is done, reverse print its right most path
 * 			say
 * 			R
 * 		  /	
 *       L
 *      / \
 *     ?   1
 *          \
 *           2
 *           
 *     print 2, 1, L
 *     
 *     This guaranteed that the root is always after it right tree.
 *     
 *     This is done when left subtree is done, so left has been covered.
 *     
 *     Therefore postorder
 *     
 *     2. Remember to restore the link to null first before reverse printing, or the temp link could cause a mess!
 *     
 *     
 *           
 */

public class BinaryTreePostorderTraversal_Morris {
	
	public static void main(String[] args) {
		BinaryTreePostorderTraversal_Morris s = new BinaryTreePostorderTraversal_Morris();
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		//TreeNode node3=new TreeNode(3);
		node1.left = node2;
		//node2.left = node3;
		List<Integer> x = s.postorderTraversal(node1);
		return;
	}

	
	
    public void reverse(TreeNode from, TreeNode to){
    	TreeNode d = new TreeNode(0);
    	d.right = from;
    	TreeNode pre = d;
        TreeNode tail = from; 
        TreeNode cur = tail.right;
        while(cur!=null){  // no need to know to, as to is the right most 
            tail.right = cur.right;
            cur.right = pre.right;
            pre.right = cur;
            cur = tail.right;
        }
    }

    public void reversePrint(TreeNode from, TreeNode to, List<Integer> res){
        reverse(from, to);
        
        //print to to from
        TreeNode node = to;
        while(node!=null){
            res.add(node.val);
            if(node==from){
            	break;
            }
            node=node.right;
        }
        
        reverse(to, from);
    }
    
    //IN SHORT: 主树变dummy左树；没左，走右；有左，找前继，记链接，走左；如前继标记自己，恢复，反向输出左孩子到前继，走右
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode node = dummy;
        TreeNode pre = null;
        while(node!=null){
            if(node.left==null){
                node = node.right;
            }else{
                //find predecessor
                pre = node.left;
                /*
                   Note that we need to look one ahead as we want to use pre 
                   Also it cannot be the current. 
                */
                while(pre.right!=null && pre.right!=node){ 
                    pre = pre.right;
                }
                if(pre.right==null){
                    //add the link, go left
                    pre.right = node;
                    node = node.left;
                }else{//pre.right == node, a previous linked back
                	pre.right = null;
                	reversePrint(node.left, pre, res);
                    node = node.right;
                }
            }
        }
        return res;
    }
}
