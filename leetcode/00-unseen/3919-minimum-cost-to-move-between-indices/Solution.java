class Solution {
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length; // n >= 2
        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];
        leftToRight[1] = 1;
        rightToLeft[n - 2] = 1;

        for (int i = 2; i < n; i++) {
            int gap = nums[i] - nums[i - 1];
            boolean discounted = gap < nums[i - 1] - nums[i - 2];
            int cost = discounted ? 1 : gap;
            leftToRight[i] = cost + leftToRight[i - 1];
        }
        for (int i = n - 3; i >= 0; i--) {
            int gap = nums[i + 1] - nums[i];
            boolean discounted = gap <= nums[i + 2] - nums[i + 1];
            int cost = discounted ? 1 : gap;
            rightToLeft[i] = cost + rightToLeft[i + 1];
        }

        int m = queries.length;
        int[] ans = new int[m];
        for(int j = 0; j < m; j++) {
            int from = queries[j][0];
            int to = queries[j][1];
            if (from <= to) {
                ans[j] = leftToRight[to] - leftToRight[from];
            } else {
                ans[j] = rightToLeft[to] - rightToLeft[from];
            }
        }

        return ans;
    }
}
