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
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); //TIP: remember to add an empty set to start
        
        for(int i=1;i<=n;i++){ //NOTE from 1 to n
            List<List<Integer>> newRes = new ArrayList<List<Integer>>();  
            for(List<Integer> cur : res){
            	int remain = n - i + 1;
            	int used = cur.size();
            	int toFill = k - used;
            	if(remain > toFill){ //if there are more to add than must add, we can go with nothing
	                List<Integer> without = new ArrayList<Integer>(cur); //TIP get used to this create new method :)
	                newRes.add(without);
            	}
            	if(toFill>0){ //if there is more to fill, we can add one
                    List<Integer> with = new ArrayList<Integer>(cur);
                    with.add(i);
                    newRes.add(with);
            	}
            }
            res = newRes;
        }
        return res;
    }
}
