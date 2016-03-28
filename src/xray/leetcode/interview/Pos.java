package xray.leetcode.interview;

public class Pos {
	public int row;
	public int col;
	public Pos(int row, int col){
		this.row = row;
		this.col = col;
	}
	@Override
	public int hashCode(){
		return Integer.hashCode(row+col);
	}
	@Override
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(! (o instanceof Pos)){
			return false;
		}
		Pos p = (Pos)o;
		return (row == p.row)&&(col==p.col);
	}
}
