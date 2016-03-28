package xray.leetcode.tree;

import java.util.*;

/*
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
 */
public class UniqueBinaryTree {
    public int numTrees(int n) {
        List<Integer> f = new ArrayList<Integer>();  //TIP if using List, then remember to add!!!
        f.add(1);  //f0 == 1
                    //fi = sigma(k=0 to i-1) f(k)f(i-1-k)
        for(int i=1;i<=n;i++){ //n times from 1
            int fi = 0;
            for(int k=0;k<i;k++){ 
                fi += f.get(k) * f.get(i-1-k);
            }
            f.add(fi);
        }
        
        return f.get(n);
    }
}
