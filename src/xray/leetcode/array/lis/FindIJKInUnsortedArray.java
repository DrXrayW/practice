package xray.leetcode.array.lis;

/*
 * The idea is to save the k-th mins and the chain, 
 * 
 * 
 */
public class FindIJKInUnsortedArray {
    public static void main(String[] args) {
         FindIJKInUnsortedArray s = new FindIJKInUnsortedArray();
          int[] A = {8,9,6,1,10,1,1,10};
          int[] x = s.getIncreasingIndex(A, 3);
          return;
   }
   
    public int [] getIncreasingIndex(int[] num, int k){
          if(num==null ){
                return null ;
         }
          int len = num.length ;
          if(len<3){
                return null ;
         }
          int[][] mins = new int[k][k];
          for(int i=0;i<k;i++){
               mins[i][i] = -1;
         }
          /*
          * mins:
          *
          * 0
          * 0 1
          * 0 1 2
          * 0 1 2 3
          *
          */
         
          for(int i=0;i<len;i++){
                int cur = num[i];
                for(int j=0;j<k;j++){
                      int minIndex = mins[j][j];
                      if(minIndex == -1 || cur <= num[minIndex]){
                            for(int m=0;m<j;m++){
                                  //copy the last line to this line, saving history
                                 mins[j][m] = mins[j-1][m];
                           }
                           mins[j][j] = i;
                            if(j==(k-1)){
                                  return mins[j];
                           }
                            break;
                     }
               }
         }
          return null ;
   }
}

