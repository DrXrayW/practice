package xray.leetcode.enumeration;

import java.util.*;
/*
 * Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); //TIP: remember to add an empty set to start
        if(S==null||S.length==0){
            return res;
        }
        Arrays.sort(S); //TIP remember to sort first as per requirement
        for(int i=0;i<S.length;i++){
            List<List<Integer>> newRes = new ArrayList<List<Integer>>();  
            for(List<Integer> cur : res){
                List<Integer> without = new ArrayList<Integer>(cur); //TIP get used to this create new method :)
                newRes.add(without);
                List<Integer> with = new ArrayList<Integer>(cur);
                with.add(S[i]);
                newRes.add(with);
            }
            res = newRes;
        }
        return res;
    }
}
