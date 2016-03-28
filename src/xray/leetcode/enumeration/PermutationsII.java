package xray.leetcode.enumeration;

import java.util.*;
/*
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();  
        if(num==null||num.length==0){
            return res;
        }
        Arrays.sort(num); //TIP must sort!!!
        permute(num, new ArrayDeque<Integer>(), 
        		new boolean[num.length], res);  //TIP use boolean array more efficient 
        return res;
    }
    
    private void permute(int[] num, ArrayDeque<Integer> head, //Use an ArrayDeque as we are moving the tail a lot
    		boolean[] used, List<List<Integer>> res) {
        if(head.size()==num.length){ //TIP all used
            //all used, full path TIP!!! return the full head as one permutation
            res.add(new ArrayList<Integer>(head));
            return;
        }
        
        for(int i=0;i<num.length;i++){
        	/*
        	 * IDEA impose an order to the same numbers: 
        	 * the 2nd same number always appears after the first one (used only the first used, so on and so forth)
        	 */
            if(i>0&&(!used[i-1])&&(num[i]==num[i-1]) ){ 
                continue;
            }
            if(!used[i]){  //IDEA pick a number that is not used add to head, and go
                used[i]=true; //TIP maintain used
                head.add(num[i]); 
                permute(num, head, used, res);
                head.removeLast(); //TIP remember to move head
                used[i]= false;   //TIP maintain used
            }
        }
    }
}
