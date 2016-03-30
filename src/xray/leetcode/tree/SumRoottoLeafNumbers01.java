package xray.leetcode.tree;

import util.Ref;

/*Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
 * 
 * IDEA: pass a path along the recursion, which notes the number of the path of the parent:
 * newPath = path * 10 + root.val
 * 
 * TIP: only add to sum when it is a leaf
 * TIP: null tree is 0
 *
 *
 * [python]
 */
public class SumRoottoLeafNumbers01 {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }
    
    private int sumNumbers(TreeNode root, int path){
        if(root==null){
            return 0;
        }
        int newPath = path*10 + root.val;
        if(root.left==null&&root.right==null){
            //leaf
            return newPath;
        }
        return sumNumbers(root.left, newPath) + sumNumbers(root.right, newPath);
    }
}
