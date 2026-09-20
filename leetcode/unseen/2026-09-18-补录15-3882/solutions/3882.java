class Solution {
    private final int RANGE = 1024;
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] set = new boolean[m][RANGE];
        boolean[] nextSet = new boolean[RANGE];
        set[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = grid[i][j];
                for (int k = 0; k < RANGE; k++) {
                    if (j > 0 && set[j - 1][k]) nextSet[k ^ num] = true;
                    if (set[j][k]) nextSet[k ^ num] = true;
                }
                for (int k = 0; k < RANGE; k++) {
                    set[j][k] = nextSet[k];
                    nextSet[k] = false;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < RANGE; k++) {
            if (set[m - 1][k]) ans = Math.min(ans, k);
        }

        return ans;
    }
}
