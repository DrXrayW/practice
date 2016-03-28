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
 * TIP: null tree do nothing
 * TIP: use a Ref type to pass sum
 */
public class SumRoottoLeafNumbers {
    public int sumNumbers(TreeNode root) {
        Ref<Integer> sum = new Ref<>(0);
        sumNumbers(root, sum, 0);
        return sum.value;
    }
    
    private void sumNumbers(TreeNode root, Ref<Integer> sum, int path){
        if(root==null){
            return;
        }
        int newPath = path*10 + root.val;
        if(root.left==null&&root.right==null){
            //leaf
            sum.value += newPath;
        }
        sumNumbers(root.left, sum, newPath);
        sumNumbers(root.right, sum, newPath);
    }
}
