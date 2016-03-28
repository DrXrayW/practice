package xray.leetcode.sort;
/*
 * only 3 colors/numbers, 
 * counting sort 
 */
public class SortColorCountSort {
    public void sortColors(int[] A) {
        if( (A==null)||(A.length==0) ){
            return;
        }
        
        int[] counts = new int[]{0,0,0};
        for(int i=0;i<A.length;i++){
            counts[A[i]]++;
        }
        int idx = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<counts[i];j++){
                A[idx] = i;
                idx++;
            }
        }
    }
}	
