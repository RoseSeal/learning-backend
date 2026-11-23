class Solution {
    private static final int MOD = 1_000_000_007;
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] nexRight = new int[n];
        int[] curDown = new int[n];
        int[] nexDown = new int[n];
        curDown[0] = 1;

        for (int i = 1; i < n; i++) {
            curDown[i] = 1;
            if (grid[0][i] == 1) break;
        }

        for (int i = 1; i < m; i++) {
            nexRight[0] = curDown[0];
            nexDown[0] = grid[i][0] == 1 ? 0 : curDown[0];

            for (int j = 1; j < n; j++) {
                nexRight[j] = curDown[j];
                nexDown[j] = nexRight[j - 1];
                if (grid[i][j] != 1) {
                    nexDown[j] = (nexDown[j] + curDown[j]) % MOD;
                    nexRight[j] = (nexRight[j] + nexRight[j - 1]) % MOD;
                }
            }

            int[] temp = curDown;
            curDown = nexDown;
            nexDown = temp;
        }

        return curDown[n - 1];
    }
}