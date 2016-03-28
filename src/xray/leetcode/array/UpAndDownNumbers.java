package xray.leetcode.array;
public class UpAndDownNumbers {
	
    public static void main(String[] args) {
        UpAndDownNumbers s = new UpAndDownNumbers();
         int[] A = {1, 2, 3, 4, 5, 6};
        int[] res = s.wiggelSort(A);
        
         return;
    }	
  
    int[] wiggelSort(int[] list) {
    	if(list==null){
    		return null;
	    }
	    int len = list.length;
	    if(len<=1){
	    	return list;
	    }
	
	    boolean up = true;
	    for(int i=0;i<len-1;i++){ //looking at pairs so ignore one
	    	if( (up&&(list[i]>list[i+1])) ||
	    		(!up&&(list[i]<list[i+1]) )){
	    		swap(list, i, i+1);
	    	}
	    	up=!up;
	    }
	
	    return list;
    }

    private void swap(int[] list, int i, int j){
    	int tmp = list[i];
    	list[i] = list[j];
    	list[j] = tmp;
    }
}
