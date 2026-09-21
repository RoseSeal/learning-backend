class Solution {
    public int almostPalindromic(String s) {
        int n = s.length();
        boolean[][] isPalind = new boolean[n][n];
        boolean[][] isAlmost = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalind[i][i] = true;
            isAlmost[i][i] = true;
        }

        int ans = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                boolean equal = s.charAt(i) == s.charAt(j);

                isPalind[i][j] = equal &&
                    (len <= 2 || isPalind[i + 1][j - 1]);
                isAlmost[i][j] = (equal && 
                    (len <= 2 || isAlmost[i + 1][j - 1]))
                    || isPalind[i][j - 1]
                    || isPalind[i + 1][j];

                if (isAlmost[i][j]) ans = len;
            }
        }

        return ans;
    }
}