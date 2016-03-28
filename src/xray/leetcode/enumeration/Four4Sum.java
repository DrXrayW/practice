package xray.leetcode.enumeration;
import java.util.*;

/*
 * IDEA add outter loop for 3 sum
 * TIP avoid dup
 * 		1. call 3 sum with the next of the first pick as start
 * 		2. if i is dup then skip
 * 
 * TIP connecting results, to avoid creating new list, we pass the number in 3 sum for it to connect the results
 * 
 */
public class Four4Sum {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null||num.length<4){
            return res;
        }
        int len = num.length;
        Arrays.sort(num);
        for(int i=0;i<len-3;i++)
        {
            if(i>0&&num[i]==num[i-1]){
                continue;
            }
            threeSum(num,i+1,target-num[i],num[i], res);
        }
        return res;
    }
    
    private void threeSum(int[] num, int start, int target, int pickedNum, List<List<Integer>> res) {
        int len = num.length;
        if(len<3){
        	return;
        }
        for(int i=start;i<len-2;i++){ //we can go - 2
            if(i>start&&num[i]==num[i-1]){
                continue;
            }
            int j=i+1;
            int k=len-1;
            while(j<k){
                int sum3 = num[i] + num[j] + num[k];
                if(sum3<target){
                    j++;
                }else if(sum3>target){
                    k--;
                }else{
                    List<Integer> sol = new ArrayList<Integer>();
                    sol.add(pickedNum);
                    sol.add(num[i]);
                    sol.add(num[j]);
                    sol.add(num[k]);
                    res.add(sol);
                    j++;
                    k--;
                    while(j<k&&num[j-1]==num[j]){
                        j++;
                    }
                    while(j<k&&num[k]==num[k+1]){
                        k--;
                    }
                }
            }
        }
    }
}
