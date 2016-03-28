package xray.leetcode.tree;

import java.util.*;

/*
 * Instead of the numbers, return the actual trees.
 * 
 * 
 * Still using this recursive formula
 * 
 * f(0) = 1
 * 
 * look at the 3 case:
 * 
 *      1
 *     / \
 * f(0)   2 f(2)
 *         \
 *          3
 *          
 *      2
 *     / \
 * f(1)   f(1)
 *                  
 *
 *      3
 *     / \
 * f(2)   f(0)
 *
 * f(3) = f(0)*f(2) + f(1)*f(1) + f(2)*f(0)
 *       
 * so we have fi = fi = sigma(k=0 to i-1): f(k)*f(i-1-k)  
 * 
 * TIP: yes we are redoing the sub tree of the same size every time, but since the values are different, we have to. 
 * 
 * TIP: add a null to the result, so that it handles the null tree case;
 * 
 * TIP: 1 2 3 4 5 6  
 * left is the left boundary of the current range
 * right is the right boundary of the current range
 * 
 * k needs to iterate through left to right 
 * 
 * with this setting, 
 * say left is 1
 * leftTree: k = 2, from 1 to 1; k=3, from 1 to 2, so it is left to k - 1, while k = 1, there is no left, so it is from 1 to k - 1 = 0, causing left>right, a null tree 
 *        
 * rightTree is similar, it is from k+1 to right. 
 * 
 * with the null tree handling, there is at least one entry even null in the left/right list, therefore, we can safely create a new node in the double loop and connect with them        
 *         
 */
public class UniqueBinaryTreeII {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n); 
    }
    /*
     * left start number
     * right end number
     */
    private List<TreeNode> generateTrees(int left, int right){
        List<TreeNode> res = new ArrayList<TreeNode>();
        
        if(left>right){ //when left == right, still one node, only when left > right, there is no node, however, no node means null tree
            res.add(null);
            return res;
        }
        
        for(int k=left;k<=right;k++){ //inclusive, so = and =, k means picking k as the root, 
            List<TreeNode> leftlist = generateTrees(left, k-1); //since k is picked for, left would be k - 1
            List<TreeNode> rightlist = generateTrees(k+1, right); //
            
            for(TreeNode leftTree : leftlist){ //thanks to the null tree, we can loop through
                for(TreeNode rightTree : rightlist){
                    TreeNode node = new TreeNode(k);  
                    node.left = leftTree;
                    node.right = rightTree;
                    res.add(node);
                }
            }
        }
        
        return res;
    }
}
