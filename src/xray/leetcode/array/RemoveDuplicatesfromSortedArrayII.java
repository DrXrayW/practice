package xray.leetcode.array;

public class RemoveDuplicatesfromSortedArrayII {
    public int removeDuplicates(int[] A) {
        if( (A==null)||(A.length==0) ){
            return 0; //ask this handling
        }
        int i = 1; //i -1 is the last unique number index, i is the index to set with a non duplicated number
        int repeatCap = 2;
        int repeat = 1;
        for(int t = 1; t < A.length;t++){
            boolean pass = false; //like a video game, you can use one of your repeats, or you can go different, either way you get a pass. 
            if(A[t]!=A[i-1]){ 
                pass = true;
                repeat = 1;  //keeping track of the repeat: reset to 1 
            }else if(repeat<repeatCap){
                pass = true;
                repeat ++; //keeping track of repeat: one more time
            }
            if(pass){
                A[i] = A[t]; 
                i++;
            }
        }
        return i;
    }
}
