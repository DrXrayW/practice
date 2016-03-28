package xray.leetcode.string;

public class StringToInteger_atoi {
    public int atoi(String str) {
        //triming?
        //heading 0
        //locale?
        //other chars?
        //null, empty
        //overflow
        //sign!!
        
        if((str==null)||(str.isEmpty())){
            return 0;
        }
        
        String s = str.trim();
        
        boolean negative = false;
        boolean signed = false;
        boolean inNumber = false;
        boolean numberFinished = false;
        long result = 0;
        for(int i=0;i<s.length();i++){        
            char c = s.charAt(i);
            switch(c){
                case '+':
                    if(signed){
                        return 0;
                    }
                    signed = true;
                    break;
                case '-':
                    if(signed){
                        return 0;
                    }
                    signed = true;
                    negative = true;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    inNumber = true;  
                    result = result * 10 + getDigit(c);
                    if( result> ((long)Integer.MAX_VALUE + 1) ) {
                        numberFinished = true;
                    }
                    break;
                default:
                    if(!inNumber){
                        return 0;
                    }
                    //inNumber
                    numberFinished = true;
                    break;
            }
            if(numberFinished){
                break;
            }
        }
        
        if(negative){
            result = - result;
        }
        
        if(result<Integer.MIN_VALUE){
            result = Integer.MIN_VALUE;
        }
        
        if(result>Integer.MAX_VALUE){
            result = Integer.MAX_VALUE;
        }


        return (int)(result);
    }
    
    private int getDigit(char c){
        switch(c){
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }
}
