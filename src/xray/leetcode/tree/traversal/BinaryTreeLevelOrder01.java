package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;

//non recursive solution, like the shortest path finding, expand one step at a time, saving all to-expand-node in a queue
/*
 * current queue is the current expanding set
 * next queue holds the next expanding set
 * [python]
 */
public class BinaryTreeLevelOrder01 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
        
        if(root==null){
            return levelOrder;
        }
        
        Queue<TreeNode> current = new LinkedList<TreeNode>();
        Queue<TreeNode> next = new LinkedList<TreeNode>();
        current.add(root);
        int level = 0;
        while(!current.isEmpty()){
            while(!current.isEmpty()){
                TreeNode node = current.poll();
                List<Integer> levelList;
                if(levelOrder.size()<=level){
                     levelList = new ArrayList<Integer>();
                     levelOrder.add(levelList);
                }else{
                    levelList = levelOrder.get(level);
                }
                levelList.add(node.val);
                
                if(node.left!=null){
                    next.add(node.left);
                }
                
                if(node.right!=null){
                    next.add(node.right);
                }
            }
            level++;
            current = next;
            next = new LinkedList<TreeNode>();
        }
        
        return levelOrder;
    }
}
