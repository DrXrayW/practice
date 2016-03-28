package xray.leetcode.math;

public class Sqrt {
    public int sqrt(int x) {
        if(x<0){
            //error handling
        }
        
        if(x<2){
            return x;
        }
        
        int left = 1;  
        int right = x; //right boundary is one greater than the largest index, cannot be x + 1 because of overflow
        int lastMid = 1; //why last Mid??
        while(left<right){
            int mid = (left + right) / 2;
            if( (x/mid) < mid ){ //x < mid * mid  //TIP: use this to avoid overflow
                right = mid;
            }else if( (x/mid)> mid ) { //x > mid * mid
                left = mid + 1;
                lastMid = mid;
            }else{
                return mid;
            }
        }
        return lastMid;
    }
}
