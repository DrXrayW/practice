package xray.leetcode.string;


public class RegularExpressionMatching03 {
	public static void main(String[] args) {
		RegularExpressionMatching03 s = new RegularExpressionMatching03();
		
		boolean  x = s.isMatch("aa", "ab+a");
		System.out.println(x);
		return;
	}

    public boolean isMatch(String s, String p) {
        int i, j;
        int m = s.length();
        int n = p.length();

        /**
         * b[i + 1][j + 1]: if s[0..i] matches p[0..j]
         * if p[j] != '*'
         * b[i + 1][j + 1] = b[i][j] && s[i] == p[j]
         * if p[j] == '*', denote p[j - 1] with x,
         * then b[i + 1][j + 1] is true iff any of the following is true
         * 1) "x*" repeats 0 time and matches empty: b[i + 1][j -1]
         * 2) "x*" repeats 1 time and matches x: b[i + 1][j]
         * 3) "x*" repeats >= 2 times and matches "x*x": s[i] == x && b[i][j + 1]
         * '.' matches any single character
         */
        boolean[][] b = new boolean[m + 1][n + 1];
        b[0][0] = true;
        for (i = 0; i < m; i++) {
            b[i + 1][0] = false;
        }
        // p[0..j - 2, j - 1, j] matches empty iff. p[j] is '*' and p[0..j - 2] matches empty
        for (j = 0; j < n; j++) {
            b[0][j + 1] = j > 0 && '*' == p.charAt(j) && b[0][j - 1];
        }

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (p.charAt(j) != '*') {
                    b[i + 1][j + 1] = b[i][j] && matchesAt(s, p, i,j);
                } else { //p.charAt(j) == '*'
                    b[i + 1][j + 1] = b[i + 1][j - 1] && j > 0 ||  //"x*" repeats 0 time and matches empty: b[i + 1][j -1], note that this jumps from j-1 to j+1, so that is the x* pattern
                    					b[i + 1][j] || //"x*" repeats 1 time and matches x: b[i + 1][j], only jump from j to j+1, matching one
                    					b[i][j + 1] && j > 0 && matchesAt(s, p , i, j-1); //"x*" repeats >= 2 times and matches "x*x": s[i] == x && b[i][j + 1], the previous one is in a matching * pattern
                }
            }
        }
        return b[m][n];
    }
    
    private boolean matchesAt(String s, String p, int si, int pi){
    	return (p.charAt(pi) == '.') || (p.charAt(pi)==s.charAt(si));
    }
}
