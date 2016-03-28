package xray.leetcode.enumeration;
import java.util.*;
/*
 * 
 * IDEA
 * 
 * 1. Sort!!!
 * 
 * 2. 
 * say i,j,k are the 3 indexes we want to find
 * pick one i from 0 to len - 3 (len -1 is the last, but leaving 2 for j and k)
 * 
 *  i........]
 *  0 1 2 3 4 5 6 
 *  1 1 2 2 3 3 4
 *    j
 *              k
 * 
 * 
 * j = i+1, k = len-1
 * 
 * test the sum of j and k, if < -num[i], means j needs to improve, j++
 * test the sum of j and k, if > -num[i], means k needs to reduce, k--
 * 
 * until we find one, 
 * 
 * TIP: avoid dup:
 * 
 * 1. Do not pick the same i if the previous one is the same
 * 2. when we found an answer, avoid using same num[j] and num[k] we had in the found answer
 * 
 * 
 * time O(n^2 + ologn) = O(n*2) 
 * space O(1)
 */
public class Three3Sum {
    public List<List<Integer>> threeSum(int[] num) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null||num.length<3){
            return res;
        }
        int len = num.length;
        Arrays.sort(num);
        for(int i=0;i<len-2;i++){ //we can go - 2
            if(i>0&&num[i]==num[i-1]){
                continue;
            }
            int j=i+1;
            int k=len-1;
            while(j<k){
                int sum2 = num[i] + num[j];
                
                if(sum2+num[k]<0){
                    j++;
                }else if(sum2+num[k]>0){
                    k--;
                }else{
                    List<Integer> sol = new ArrayList<Integer>();
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
        return res;
    }
}
