class Solution {
    private static final int MAXCOST = 80 * 80 * 10_000;
    private int offset;
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        offset = n;
        List<Integer> jump = buildJump(grid);
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], MAXCOST);
        dp[1][1] = 0;
        runDp(dp, grid);

        for (int idx = 0; idx < k; idx++) {
            if(!doJump(jump, dp, grid)) break;
            runDp(dp, grid);
        }

        return dp[m][n];
    }

    private List<Integer> buildJump(int[][] grid) {
        int m = grid.length;
        if (m == 0) return new ArrayList<>();
        int n = grid[0].length; // offset = n
        List<Integer> jump = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                jump.add(i * offset + j);
            }
        }
        // 按grid的值降序排序
        jump.sort((a, b) -> {
            int va = grid[a / offset][a % offset];
            int vb = grid[b / offset][b % offset];
            return Integer.compare(vb, va);
        });
        return jump;
    }

    private boolean doJump(List<Integer> jump, int[][] dp, int[][] grid) {
        boolean jumped = false;
        int idx = 0;
        int len = jump.size();
        
        // 初始化全局最小代价：从第一个元素开始
        int firstPos = jump.get(0);
        int minCost = dp[firstPos / offset + 1][firstPos % offset + 1];
        
        while (idx < len) {
            int curPos = jump.get(idx);
            int i = curPos / offset;
            int j = curPos % offset;
            int curVal = grid[i][j];
            int groupEnd = idx + 1;
            
            // 计算本组的最小dp
            int groupMin = dp[i + 1][j + 1];
            while (groupEnd < len) {
                int ePos = jump.get(groupEnd);
                int ei = ePos / offset;
                int ej = ePos % offset;
                if (grid[ei][ej] != curVal) {
                    break;
                }
                groupMin = Math.min(groupMin, dp[ei + 1][ej + 1]);
                groupEnd++;
            }
            
            if (groupMin < minCost) {
                minCost = groupMin;
            }
            
            // 用全局最小代价更新本组
            boolean updated = false;
            for (int k = idx; k < groupEnd; k++) {
                int p = jump.get(k);
                int pi = p / offset;
                int pj = p % offset;
                if (dp[pi + 1][pj + 1] > minCost) {
                    dp[pi + 1][pj + 1] = minCost;
                    updated = true;
                }
            }
            if (updated) jumped = true;
            
            idx = groupEnd;
        }
        return jumped;
    }



    private void runDp(int[][] dp, int[][] grid) {
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i == 1 && j == 1) continue;
                dp[i][j] = Math.min(
                    dp[i][j],
                    Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]
                );
            }
        }
    }
}