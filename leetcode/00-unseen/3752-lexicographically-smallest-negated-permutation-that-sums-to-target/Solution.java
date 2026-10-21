class Solution {
    public int[] lexSmallestNegatedPerm(int n, long target) {
        long sum = (long)n * (n + 1) / 2;
        int[] ans = new int[n];
        boolean[] used = new boolean[n + 1];
        int idx = 0;
        
        for (int i = n; i > 0; i--) {
            if (sum - i * 2 >= target) {
                sum -= i * 2;
                ans[idx++] = -i;
                used[i] = true;
            }
        }
        if (sum != target) return new int[0];

        for (int i = 1; i <= n; i++) {
            if (!used[i]) ans[idx++] = i;
        }
        return ans;
    }
}