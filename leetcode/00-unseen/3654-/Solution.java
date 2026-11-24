class Solution {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + nums[i];

        int[] map = new int[k];
        Arrays.fill(map, -1);
        map[0] = 0;

        long[] dp = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int mask = (int)(preSum[i + 1] % k);
            int j = map[mask];
            dp[i + 1] = dp[i] + nums[i];

            if (j != -1) {
                dp[i + 1] = Math.min(dp[i + 1], dp[j]);
            }

            if (j == -1 || dp[i + 1] < dp[j]) {
                map[mask] = i + 1;
            }
        }

        return dp[n];
    }
}