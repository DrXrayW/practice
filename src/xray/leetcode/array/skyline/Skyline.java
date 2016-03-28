package xray.leetcode.array.skyline;

public class Skyline{
    public int left, right, height;

    public Skyline(int left, int right, int height) {
      this.left = left;
      this.right = right;
      this.height = height;
    }
    
    @Override
    public String toString(){
    	return "[" + left + ", " + right + "]: " + height;
    }
}
