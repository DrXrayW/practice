package xray.leetcode.tree.path;

import java.util.*;

import xray.leetcode.tree.TreeNode;

public class PathSumII01 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null){
            return result;
        }
        
        pathSum(root, new Stack<Integer>(), result, sum);
        return result;
    }
    
    private void pathSum(TreeNode root, Stack<Integer> current, List<List<Integer>> result, int sum){
        if(root==null){
            return;
        }
        
        current.push(root.val); //pre-recursion
        
        /*
         * note that we are detecting leaf node here.  If we are going to stop at sum == 0 and current = null, then we will have dup for left and right.
         */
        if( (root.left==null)&&(root.right==null) ) { 
           if(root.val == sum){
               result.add(new ArrayList<Integer>(current)); //here is the trick to clone the current list
           }
           current.remove(current.size() - 1);   //remember to remove the last one whenever we return after add
           return;
        }
        
        pathSum(root.left, current, result, sum - root.val);
        pathSum(root.right, current, result, sum - root.val);
        
        
        current.pop(); //remember to remove the last one whenever we return after add //post recursion
    }
}
