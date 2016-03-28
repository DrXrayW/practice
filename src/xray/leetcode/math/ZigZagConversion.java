package xray.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public String convert(String s, int nRows) {
        if(s==null){
            return null;
        }
        
        if(nRows==0){
            return ""; //ask question about this
        }
        
        if(nRows==1){
            return s;
        }
        
        List<StringBuilder> resultList = new ArrayList<StringBuilder>();
        
        for(int i=0;i<nRows;i++){
            resultList.add(new StringBuilder());
        }
        
        //tip 1: where the char should go to purely depends on its index/position in the string
        for(int i=0;i<s.length();i++){
            int row = getRow(i, nRows);
            resultList.get(row).append(s.charAt(i));
        }

        StringBuilder buf = new StringBuilder();
        for(StringBuilder sb : resultList){
            buf.append(sb.toString());
        }
        return buf.toString();
    }
    
    private int getRow(int k, int nRows){
    	//tip 2: the row is periodic
        int num = k % (nRows*2 - 2);
        if(num<nRows){
            return num;
        }else{
        	//tip 3: (nRows - 1) - (num - nRows +1)
        	//m - n
        	//m - : the part needs to reverse the index order, i.e. change from 3,2,1,0 to 0,1,2,3
        	//n : (num - nRows) = what is left over nRows, +1 = start from the second bottom
        	/*
        	 * 0     :3     
        	 * 1   5 :2
        	 * 2 4   :1
        	 * 3     :0
        	 */
            return 2*nRows - 2 - num;   
        }
    }
}
