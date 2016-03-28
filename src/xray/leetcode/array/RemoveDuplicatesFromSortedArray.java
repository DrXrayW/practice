package xray.leetcode.array;
/*
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.


 * IN SHORT
scan, put the first that is not the same as the same one in position. 
use i as the index to put, which is also the final length. 


 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        if( (A==null)||(A.length==0) ){
            return 0; //ask this handling
        }
        
        int cur = 1; //i -1 is the last unique number index, i is the index to set with a non duplicated number
        for(int i = 1; i < A.length;i++){
            if(A[i]!=A[cur-1]){
                A[cur] = A[i]; //always put the first one that is different than i-1 to i
                cur++;
            }
        }
        return cur;
    }
}	
