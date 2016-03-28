package xray.leetcode.array;

public class CompleteLoop {
	public static void main(String[] args) {
		CompleteLoop s = new CompleteLoop();
		int[] arr = {0};
		boolean a1 = s.isCompleteLoop(arr);
		System.out.println(a1);

		int[] arr2 = {1,1,1,1,1};
		boolean a2 = s.isCompleteLoop(arr2);
		System.out.println(a2);

		int[] arr3 = {1,1,1,-1004,0};
		boolean a3 = s.isCompleteLoop(arr3);
		System.out.println(a3);

		return;
	}
	boolean isCompleteLoop(int[] arr){
		if(arr==null){
			return false;
		}
		int len = arr.length;
		if(len==0){
			return false;
		}
		int count = 1;
		int index = getIndex(0, arr); //start from 1st move, if can reach n move and that is the first time it goes back then true, also you want to stop 
		while(index!=0){
			index = getIndex(index, arr);
			count++;
			if(count>len){
				break;
			}
		}
		return (count==len);
	}
	
	int getIndex(int index, int[] arr){
		int len = arr.length;
		int newIndex = (index + arr[index]) % len;
		if(newIndex<0){
			newIndex = len + newIndex; //-5 % 3 = -2, so 3 + - 2 = 1  
		}
		return newIndex;
	}
}
