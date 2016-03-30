package xray.leetcode.tree.path;

import xray.leetcode.tree.TreeNode;
/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Subscribe to see which companies asked this question

IDEA: pass the sum down, deducting the root val, if reaching leaf and it is 0, then return true.

[python]

 */

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        /*
        note that we cannot determine the sum == 0 here,
        as we do not know whether the parent is a leaf (it may have another child)
         */
        if(root==null){ //this is necessary for leaf node,
            return false;
        }
        
        int subSum = sum - root.val;
        
        //leaf, ending recursion
        if( (root.left==null)&&(root.right==null) )  {
            if(subSum == 0){
                return true;
            }
        }
        
        //note that we cannot go return hasPathSum, as if it doesn't work in the left, we need to pass it to the right side
        if(root.left!=null){
            if(hasPathSum(root.left, subSum)){
                return true;
            }
        }
        
        if(root.right!=null){
            if(hasPathSum(root.right, subSum)){
                return true;
            }
        }
        
        return false;
    }
}
