package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;
/*
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

[python]

 */
public class BinaryTreeLevelOrderII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
        levelOrderTop(levelOrder, root, 0);
        Collections.reverse(levelOrder); //TIP remember this!!
        return levelOrder;
    }
    
    public void levelOrderTop(List<List<Integer>> levelOrder, TreeNode node, int depth){
        if(node==null){
            return;
        }
        
        //put the current node in list
        List<Integer> level;
        if(levelOrder.size()<=depth){
            level = new ArrayList<Integer>();
            levelOrder.add(level);
    	}else{
    		level = levelOrder.get(depth);
    	}

        level.add(node.val);
        
        //go left and right
        levelOrderTop(levelOrder, node.left, depth + 1);
        levelOrderTop(levelOrder, node.right, depth + 1);
    }
}
