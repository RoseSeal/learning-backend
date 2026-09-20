class Solution {
    public int countLocalMaximums(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][][] maxVal = new int[n][m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int max = matrix[i][j];
                for (int k = j; k < m; k++) {
                    max = Math.max(max, matrix[i][k]);
                    maxVal[i][j][k] = max;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = matrix[i][j];
                if (curr > 0) {
                    int up = i - curr;
                    int down = i + curr;
                    int left = j - curr;
                    int right = j + curr;
                    int leftP = left + 1 < 0 ? 0 : left + 1;
                    int rightP = right - 1 >= m ? m - 1 : right - 1;
                    left = left < 0 ? 0 : left;
                    right = right >= m ? m - 1 : right;
                    if (up < 0) up = 0;
                    else if (maxVal[up][leftP][rightP] > curr) {
                        continue;
                    }
                    else up++;
                    if (down >= n) down = n - 1;
                    else if (maxVal[down][leftP][rightP] > curr) {
                        continue;
                    }
                    else down--;
                    boolean isAns = true;
                    while (up <= down) {
                        if (maxVal[up][left][right] > curr) {
                            isAns = false;
                            break;
                        }
                        up++;
                    }
                    if (isAns) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
