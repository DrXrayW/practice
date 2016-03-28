package xray.leetcode.array;
/*
 * Remove elements of the elem value
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.

IN SHORT: //scan the list, only put the filtered ones in, maintain a length/new position index 


 */
public class RemoveElement {
	//scan the list, only put the filtered ones in
    public int removeElement(int[] A, int elem) {
        if(A==null){
            return 0;
        }
        
        int cur = 0;
        for(int i=0;i<A.length;i++){
            if(A[i]!=elem){
                A[cur] = A[i];
                cur ++;
            }
        }
        return cur;
    }
}
