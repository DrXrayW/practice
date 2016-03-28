package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;

public class BinaryTreePostorderTraversal_Stack {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> lst = new ArrayList<Integer>();
  
         if(root == null)
             return lst; 
  
         Stack<TreeNode> stack = new Stack<TreeNode>();
         stack.push(root);
  
         TreeNode prev = null; //TIP: the previously visited node (not necessarily the output node)
         //IN SHORT 3种情况看 pre
         while(!stack.empty()){
             TreeNode curr = stack.peek(); //TIP only peek, not pop, only pop when we need the result, (or we can pop, but push back on the left to right visit)
  
             // go down the tree.
             //check if current node is leaf, if so, process it and pop stack,
             //otherwise, keep going down
             /*
              * prev == null, this is the root
              * 
              * prev.left == curr || prev.right == curr: as we go down the tree, the current one is a left/right child of prev
              * 
              * note that left and right are not pushed together: right is skipped if there is a left
              * 
              * if it is a leaf, pop and process
              * 
              * IN SHORT:
              * Going down, Left, Right then Self
              * 
              */
             if(prev == null || prev.left == curr || prev.right == curr){
                 if(curr.left != null){
                     stack.push(curr.left);
                 }else if(curr.right != null){
                     stack.push(curr.right);
                 }else{
                     stack.pop();
                     lst.add(curr.val);
                 }
  
             //go up the tree from left node    
             //need to check if there is a right child
             //if yes, push it to stack
             //otherwise, process parent and pop stack
                 /*
                  * if we are going up, from left tree (prev is current left child, we have processed the left tree, root is its last node)
                  * 
                  * but this one needs to go after the right tree, so push right
                  * 
                  * unless there is no right tree, then the node gets its turn
                  * 
                  * IN SHORT: Going up from left, Right then Self
                  */
             }else if(curr.left == prev){ 
                 if(curr.right != null){
                     stack.push(curr.right);
                 }else{
                     stack.pop();
                     lst.add(curr.val);
                 }
  
             //go up the tree from right node 
             //after coming back from right node, process parent node and pop stack. 
             /*
              * going up, from the right tree, again, right tree finished, its root is our right child, 
              * 
              * finally it is my turn!
              * 
              * IN SHORT: Going up from right, my turn!
              * 
              */
             }else{// if(curr.right == prev) //TIP: this condition is redundant, but here to be clear, because prev must be linked to the cur!!
                 stack.pop();
                 lst.add(curr.val);
             }
  
             prev = curr; //TIP do not forget this iterator!!!
         }
  
         return lst;
     }
}
