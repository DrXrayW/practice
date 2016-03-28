package xray.leetcode.dp;


public class MaximumProductSubarray {
    public int maxProduct(int[] A) {
        int maxProduct = Integer.MIN_VALUE;
        int product = 1;
        Integer productn = null;
        for(int i=0;i<A.length;i++){
            if(productn!=null){
                productn*=A[i];
                if(productn>maxProduct){
                    maxProduct = productn;
                }
            }
            
            product*=A[i];
            if(product>maxProduct){
                maxProduct = product;
            }
            
            if(A[i]==0){
                product=1;
                productn=null;
            }
            
            if((productn==null)&&(product<0)){
                productn = product;
                product = 1;
            }
        }
        return maxProduct;
    }
}
