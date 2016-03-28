package xray.leetcode.array;
/*
 * In SHORT
 * 
 * allowing dup at most twice
This is extendable to k times


 *  a door keeper, if not same then refresh pass, 
 * if same and still have pass, use 1 pass, both case leads to fill, i.e. putting A[t] to A[i] , 
 * otherwise ignore. next t please
 */
public class RemoveDuplicatesfromSortedArrayII01 {
    public int removeDuplicates(int[] A) {
        if( (A==null)||(A.length==0) ){
            return 0; //ask this handling
        }
        int cur = 1; //cur -1 is the last unique number index, cur is the index to set with a non duplicated number
        int repeatCap = 1; //dup twice, repeating once
        int pass = repeatCap; //like a video game, you can use one of your repeats, or you can go different, either way you get a pass.
        for(int i = 1; i < A.length;i++){
            boolean fill = false;           
            if(A[i]!=A[cur-1]){
                pass = repeatCap; //refresh your pass
                fill = true;
            }else if(pass>0){
                pass--;
                fill = true;
            }
            if(fill){
                A[cur] = A[i];
                cur++;
            }
        }
        return cur;
    }
}
