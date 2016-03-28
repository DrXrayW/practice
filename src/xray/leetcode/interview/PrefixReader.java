package xray.leetcode.interview;

public class PrefixReader {
	public static void main(String[] args) {
		PrefixReader reader = new PrefixReader(new char[]{'0', '1'}, 2);
		while(reader.hasNext()){
			System.out.println(reader.read());
		}
	}
	private char[] cs;
	private int n;
	private int k; //alphabet size
	private long r = 1; //read the current then move
	private long max = 0;
	public PrefixReader(char[] cs, int n) {
		this.cs = cs;
		this.n = n;
		k = cs.length;
		max = getMax();
	}
	private long getMax() {
		for(int i=1;i<=n;i++){
			max += (long)Math.pow(k, i);
		}
		return max;
	}
	public boolean hasNext() {
		return r<=max;
	}
	public String read() {
		if(!hasNext()){return null;}
        String res = getStr(r);
        r++;
        return res; 
	}
	
	public String getStr(long x){
		StringBuilder buf = new StringBuilder();
        while(x>=0){
            int d = (int)(x % k); 
            if(d==0){
            	d=k;
            }
            char c = cs[d-1];
            buf.append(c);
            x-=d; //deduct from remainder
            x/=k;
            if(x==0){
                break;
            }
        }
        return buf.reverse().toString();
	}
}
