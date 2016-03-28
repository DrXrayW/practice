package xray.leetcode.math;

public class Sqrt01 {
	public static void main(String[] args) {
		
	}
    public int sqrt(int x) {
        if(x<0) return -1;  
        if(x==0) return 0;  
        int l=1;
        /*
         *  //TIP: this can be x, (x + 1 would overflow for max), but as long as this is > than the non-rounded x/2 it is fine. 
         *  the min one is x /2 +1, for odd, it is 0.5 more than x/2, for even it is 1 more than x/2;
         *  
         */
        int r=x/2 +1;   
        while(l<=r){ //here we use including range at right  
            int m = (l+r)/2;  //TIP (l + r) / 2, is either the mid (odd), or the left mid (even), so in the worst case, it falls on the left index 
            if(m<=x/m && x/(m+1)<m+1){   //TIP: m*m<= x < (m+1)*(m+1);
                return m;  
            }
            if(x/m<m){  //x < m*m
                r = m-1;  
            }  
            else{  // x>=(m+1)(m+1)
                l = m+1;  
            }  
        }  
        return 0; 
    }
}
