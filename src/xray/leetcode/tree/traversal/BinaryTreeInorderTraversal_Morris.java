package xray.leetcode.tree.traversal;

import java.util.*;

import xray.leetcode.tree.TreeNode;

/*
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
 * IN SHORT: 没左，输出，走右；有左，找前继，记链接，走左；如前继标记自己，恢复，输出，走右
 * 
 */
		
public class BinaryTreeInorderTraversal_Morris {
	public static void main(String[] args) {
		BinaryTreeInorderTraversal_Morris s = new BinaryTreeInorderTraversal_Morris();
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		node1.left = node2;
		List<Integer> x = s.inorderTraversal(node1);
		return;
	}
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        TreeNode node = root;
        TreeNode pre = null;
        while(node!=null){
            if(node.left==null){
                res.add(node.val);
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
                    res.add(node.val);
                    pre.right = null;
                    node = node.right;
                }
            }
        }
        return res;
    }
}
