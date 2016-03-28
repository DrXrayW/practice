package xray.leetcode.enumeration;

import java.util.*;

/*
 * IDEA same as subset problem, but using the number itself
 * 
 * TRICK is not all subset, but only k size
 * 
 * TIP only expand when size < k
 * 
 * TIP last iteration, only put in k length (or there will be short ones)
 *
 */
public class Combinations01 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); //TIP: remember to add an empty set to start
        
        for(int i=1;i<=n;i++){ //NOTE from 1 to n
            List<List<Integer>> newRes = new ArrayList<List<Integer>>();  
            for(List<Integer> cur : res){
                List<Integer> without = new ArrayList<Integer>(cur); //TIP get used to this create new method :)
                if(i!=n||without.size()==k){
                    newRes.add(without);
                }
                if(cur.size()<k){
                    List<Integer> with = new ArrayList<Integer>(cur);
                    with.add(i);
                    if(i!=n||with.size()==k){
                        newRes.add(with);
                    }
                }
            }
            res = newRes;
        }
        return res;
    }
}
