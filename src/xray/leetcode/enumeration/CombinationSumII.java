package xray.leetcode.enumeration;
import java.util.*;
/*
 * 
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 


 * Unlike permutation, this won't go back, so a start number is enough to control dup/ordering, 
 * no need to use a used[i], 
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] num, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null){
            return res;
        }
        Arrays.sort(num); //TIP: needs to discuss 0, repeat, etc.
        return dfs(num, 0, target, new ArrayList<Integer>(), res);
    }
    
    private List<List<Integer>> dfs(int[] candidates, int start, int target, List<Integer> set, List<List<Integer>> res){
         if(target==0){ //TIP always put termination first!!! 
            res.add(new ArrayList<Integer>(set)); //TIP copy the set so far and add it to result, only need to copy here. 
            return res;
        }
        if(target<0|| start>=candidates.length){ //TIP validation should happen AFTER termination check! because it could be the trival solution passed in (where start>=candidates.length, but we already have targe = 0)
            return res; 
        }

        /*
         * TIP important to use i=start, this prevents dup:  say we have {2,3}, if we first pick 2, then we pick 3, if we start with 0 instead of start, we could pick 3 then 2.
         * this imposes an order to the recursion, and therefore avoids dups
         * 
         * TIP if there are dup numbers in the candidates, we will not go into the later ones, this is a trick for dup numbers in the candidate set, 
         * (note that we simply do not want dups here; but there are cases we need to decide whether the previous one is used)
         */
        for(int i=start;i<candidates.length;i++){     
        	/*
        	 * TIP why i>start, not i>1?? 
        	 * Now we are considering expanding
        	 * candidates to reduce are start till the end 
        	 * say we have
        	 *        start
        	 *         v
        	 * 1 1 2 3 3 3 4 4 5 5 6 6 6 7  
        	 * 
        	 * we will certainly skip the ones same as the previous one to avoid dups. 
        	 * 
        	 * the start+1 will have its turn, when the start is reduced from the target, and the recursion goes for start +1.  
        	 * 
        	 * This is imposing an order on the dups: only when the first is used, the 2nd one can be used. 
        	 * 
        	 */
            if(i>start &&candidates[i-1]==candidates[i]){   
                continue;
            }
            set.add(candidates[i]); //TIP: no need to create a new set here, we will only have a copy when adding to result, but remember to add before recursion
            dfs(candidates, i+1, target-candidates[i], set, res); //TIP: not allow dup index, start from the next one.  
            set.remove(set.size()-1); //TIP remove after recursion
        }
        return res;
    }
}
