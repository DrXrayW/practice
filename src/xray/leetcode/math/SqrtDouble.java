package xray.leetcode.math;

public class SqrtDouble {
	public static void main(String[] args) {
		SqrtDouble s = new SqrtDouble();
		double x = s.sqrt(0.00001d, 0.00001);
		System.out.println(x);
		return ;
	}
    public double sqrt(double x, double error) {
    	if(x<1d){
    		return 1d/sqrt(1d/x, error); //handle 0-1
    	}
        if(x<0){ return -1d;}  
        if(x==0d){ return 0d;}  
        double l = 0d;
        /*
         *  //TIP: this can be x, (x + 1 would overflow for max), but as long as this is > than the non-rounded x/2 it is fine. 
         *  the min one is x /2 +1, for odd, it is 0.5 more than x/2, for even it is 1 more than x/2;
         *  
         */
        double r=x;   
        while(l!=r){ //here we use including range at right  
            double m = (l+r)/2;  //TIP (l + r) / 2, is either the mid (odd), or the left mid (even), so in the worst case, it falls on the left index
            double lower = Math.max(0d, m-error);
            double upper = m+error;
            if(lower<=x/lower && x/(upper)<upper){   //TIP: m*m<= x < (m+1)*(m+1);
                return m;  
            }
            if(lower>x/lower){  //x < m*m
                r = m;  
            }  
            else{  // x>=(m+1)(m+1)
                l = m;  
            }  
        }  
        return l; 
    }
}
