package xray.leetcode.enumeration;

import java.util.*;

/*
 * Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

 */
public class Permutations {
    public List<List<Integer>> permute(int[] num) {
        if(num==null||num.length==0){
            return null;
        }
        return permute(num, new ArrayDeque<Integer>(), 
        		new HashSet<Integer>(), new ArrayList<List<Integer>>());
    }
    
    private List<List<Integer>> permute(int[] num, ArrayDeque<Integer> head, 
    		Set<Integer> used, List<List<Integer>> res) {
        if(used.size()==num.length){ //TIP all used
            //all used, full path TIP!!! return the full head as one permutation
            res.add(new ArrayList<Integer>(head));
            return res;
        }
        
        for(int i=0;i<num.length;i++){
            if(!used.contains(i)){  //IDEA pick a number that is not used add to head, and go
                used.add(i);
                ArrayDeque<Integer> nextHead = new ArrayDeque<Integer>(head); //TIP copy the head, do not pass around
                nextHead.add(num[i]);
                permute(num, nextHead, used, res);
                used.remove(i);
            }
        }
        return res;
    }
}
