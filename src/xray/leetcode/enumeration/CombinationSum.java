package xray.leetcode.enumeration;
import java.util.*;
/*
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 


 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates==null){
            return res;
        }
        Arrays.sort(candidates); //TIP: needs to discuss 0, repeat, etc.
        return dfs(candidates, 0, target, new ArrayList<Integer>(), res);
    }
    
    private List<List<Integer>> dfs(int[] candidates, int start, int target, List<Integer> set, List<List<Integer>> res){
        if(target<0){ //this is important, or there is no end
            return res; 
        }
        if(target==0){
            res.add(new ArrayList<Integer>(set)); //TIP copy the set so far and add it to result, only need to copy here. 
        }
        
        /*
         * TIP important to use i=start, this prevents dup:  say we have {2,3}, if we first pick 2, then we pick 3, if we start with 0 instead of start, we could pick 3 then 2.
         * this imposes an order to the recursion, and therefore avoids dups
         * 
         * TIP if there are dup numbers in the candidates, we will not go into the later ones, this is a trick for dup numbers in the candidate set, 
         * (note that we simply do not want dups here; but there are cases we need to decide whether the previous one is used)
         */
        for(int i=start;i<candidates.length;i++){     
            if(i>1&&candidates[i-1]==candidates[i]){  //this can be i>start ..., 
                continue;
            }
            set.add(candidates[i]); //TIP: no need to create a new set here, we will only have a copy when adding to result, but remember to add before recursion
            dfs(candidates, i, target-candidates[i], set, res); //TIP: always recursion with start, so that we can explore the same one repeatedly 
            set.remove(set.size()-1); //TIP remove after recursion
        }
        return res;
    }
}
