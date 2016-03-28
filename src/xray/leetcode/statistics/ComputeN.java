package xray.leetcode.statistics;

public class ComputeN {
	private static double p = 0.0001;
	private int compute(int n){
		return 0; //dummy
	}
	public int computeN(int n){
		int sum = 0;
		for(int i=0;i<n;i++){
			sum += computeN(i);
		}
		return sum;
	}
	/*
	 * taking the example of majority vote of 3
	 * 
	 * its error rate is 
	 * 3(1-p)p^2 + p^3
	 * = 3p^2 - 2p^3
	 * ~ 3p^2
	 * 
	 * so the idea is to break n into k segments, taking majority vote for each segment
	 * 
	 * so for (np/k) problem
	 * 
	 * it is 
	 * 3*n*n*p*p/k/k * k < p
	 * k > 3 * n * n * p
	 * 
	 * So we need that many segments
	 * 
	 */
	public int computeNReliable(int n){
		int sum = 0;
		Double dn = (double)n;
		Double dk = (3*dn*dn*p);
		int k = (int)dk.longValue() + 1; //so that k > 3 * n * n * p 
		int len = n/k; 
		if(len == 0){
			//we need to increase the inner accuracy
		}else{
			//we can go with len
		}
		/*
		 * when n/k == 0? n<k
		 * 
		 * 3pn^2 > n
		 * 3pn^2 - n + 1 > 0
		 * 
		 * p > 1/3n
		 * len = 0;
		 *
		 * if error rate is pretty big, then we have to divide many times to make it < p
		 * 
		 * n < 1 / 3p , then majority vote can make the sum error rate < p
		 * 
		 * so for n > 1/3p, divide n into sections of 1/3p, then each sum has error rate < p, then n = 3np recursion
		 * 
		 * if n < 1/3p, calculate len 
		 * 
		 * 
		 * 
		 */
		for(int i=0;i<n;i++){
			sum += computeN(i);
		}
		return sum;
	}
	
	
}
