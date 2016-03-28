package xray.leetcode.math;

public class PowXN {
    //TIP: x^n = x^{n/2} * x^{n/2} * x^{n%2}
    public double pow(double x, int n) {
        long k = (long)n;  //TIP: use long to avoid the Integer.MIN_VALUE trap
        if(k<0){
            return 1.0 / power(x, -k); //TIP: remember to deal with negative
        }
        return power(x,n);
    }
    
    private double power(double x, long k){
        assert(k>=0);
        if(k==0){
            return 1.0;  //TIP: needs to deal with end of recursion
        }
        double v = power(x, k/2);
        double result = v*v;
        if(k%2==1){
            result*=x;  //TIP: here is where k == 1 is handled.  
        }
        return result;
    }
}
