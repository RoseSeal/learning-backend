class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long[] dp = new long[3];
        long[] max = new long[3];
        Arrays.fill(max, Long.MIN_VALUE);
        for (int num : nums) {
            long pre0 = Math.max(dp[0], 0);
            long pre1 = Math.max(dp[1], 0);
            long pre2 = Math.max(dp[2], 0);

            dp[0] = pre0 + num;
            dp[1] = Math.max(pre1, pre0) + num / k;
            dp[2] = Math.max(pre2, Math.max(pre0, pre1)) + num;

            max[0] = Math.max(max[0], dp[0]);
            max[1] = Math.max(max[1], dp[1]);
            max[2] = Math.max(max[2], dp[2]);
        }
        return Math.max(max[0] * k, Math.max(max[1], max[2]));
    }
}
