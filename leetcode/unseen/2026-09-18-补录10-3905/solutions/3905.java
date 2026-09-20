class Solution {
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] ans = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        int[][] times = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(times[i], Integer.MAX_VALUE);
        int time = 0;
        for (int[] s : sources) {
            int i = s[0];
            int j = s[1];
            ans[i][j] = s[2];
            times[i][j] = time;
            queue.offer(new int[]{i, j});
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int idx = 0; idx < size; idx++) {
                int[] curr = queue.poll();
                int i = curr[0];
                int j = curr[1];
                int color = ans[i][j];
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if (ni < 0 || ni >= n ||
                        nj < 0 || nj >= m
                    ) continue;
                    if (ans[ni][nj] != 0) {
                        if (times[ni][nj] == time) {
                            ans[ni][nj] = Math.max(ans[ni][nj], color);
                        }
                    } else {
                        ans[ni][nj] = color;
                        times[ni][nj] = time;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }

        return ans;
    }
}
