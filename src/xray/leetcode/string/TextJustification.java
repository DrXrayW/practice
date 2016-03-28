package xray.leetcode.string;
import java.util.*;

/*
 * 
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.


 * IDEA:
 * get enough for the current line
 * 	process the current line
 * 		1. only one word
 * 		2. last line
 * 		3. not last line, more than one words
 * 
 * 
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        
        if(L<=1){
            for(String w: words){
                res.add(w);
            }
            return res;
        }
        
        int start = 0;
        /*
         * 
         * cat is running  |
         * 
         * wordsLength = cat+is+running
         * lineLength = cat+1+is+1+running, i.e. with intervals
         * 
         * so L - lineLength is the count of extra spaces
         * 
         * 
         * very fast.      |
         * 
         */
        while(start<words.length){
            //get words for this line
            int wordsLength = 0;
            int lineLength = 0;
            int firstLength = words[start].length();
            int end = start;
            wordsLength+=firstLength;
            lineLength+=firstLength;
            for(int i=start+1;i<words.length;i++){
                int wordLen = words[i].length();
                if(lineLength + wordLen + 1 <=L){
                    //add to the line
                    wordsLength+=wordLen;
                    lineLength+=wordLen+1;
                    end = i;
                }else{
                    break;
                }
            }
            
            if(start==end){
                //need to fill in the rest
                int spaceCount = L - wordsLength;
                StringBuilder b = new StringBuilder(words[start]);
                for(int i=0;i<spaceCount;i++){
                    b.append(" ");            
                }
                res.add(b.toString());
            }else if(end==words.length-1){ //last line
                StringBuilder b = new StringBuilder();
                for(int i=start;i<end;i++){
                    b.append(words[i]);
                    b.append(" ");
                }
                b.append(words[end]);
                int spaceCount = L - lineLength;
                for(int i=0;i<spaceCount;i++){
                    b.append(" ");            
                }
                res.add(b.toString());
            }else{
                //more than 1 words
                int wordCount = end - start + 1;
                int intervalCount = wordCount - 1;
                int spaceCount = L - wordsLength;
                int interval = spaceCount / intervalCount;
                int extraSpace = spaceCount % intervalCount;
                StringBuilder b = new StringBuilder();
                for(int i=0;i<intervalCount;i++){
                    b.append(words[i+start]);
                    for(int j=0;j<interval;j++){
                        b.append(" ");
                    }
                    if(i<extraSpace){
                        b.append(" ");
                    }
                }
                b.append(words[end]);
                res.add(b.toString());
            }
            start = end + 1;
        }
        return res;
    }
}
