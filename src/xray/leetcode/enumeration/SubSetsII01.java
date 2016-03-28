package xray.leetcode.enumeration;

import java.util.*;

/*
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
public class SubSetsII01 {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null){
            return res;
        }
        res.add(new ArrayList<Integer>()); //empty set
        if(num.length==0){
            return res;
        }
        
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for(int i=0;i<num.length;i++){
            Integer count = counts.get(num[i]);
            if(count == null){
                count = 0;
            }
            counts.put(num[i], count+1);
        }
        
        for(int val : counts.keySet()){
            int count = counts.get(val);
            List<List<Integer>> newRes = new ArrayList<List<Integer>>();
            
            for(List<Integer> curset : res){
                for(int j=0;j<=count;j++){ //empty to count
                    List<Integer> newset = new ArrayList<Integer>(curset);
                    for(int k=0;k<j;k++){ //add j of the val set
                        newset.add(val);
                    }
                    newRes.add(newset);
                }
            }
            res = newRes;
        }
        
        return res;   
    }
}
