class Solution {
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[n + 2];
        for (int num : nums) count[num]++;
        int[] biggerCount = new int[n + 2];
        for (int idx = n; idx > 0; idx--) {
            biggerCount[idx] = count[idx + 1] + biggerCount[idx + 1];
        }

        boolean[] ans = new boolean[n];
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        for (int x = 1; x <= n; x++) {
            runDp(dp, x, count[x], k);
            if (dp[k]) {
                for (int i = x; i <= n; i++) ans[i - 1] = true;
                break;
            }
            
            int maxT = Math.min(biggerCount[x], k / x);
            for (int t = 0; t <= maxT; t++) {
                if (dp[k - t * x]) {
                    ans[x - 1] = true;
                    break;
                }
            }
        }

        return ans;
    }

    private void runDp(boolean[] dp, int num, int count, int target) {
        for (int i = 0; i < count; i++) {
            for (int idx = target - num; idx >= 0; idx--) {
                dp[idx + num] = dp[idx + num] || dp[idx];
                if (dp[target]) break;
            }
            if (dp[target]) break;
        }
    }
}