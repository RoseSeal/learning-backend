class Solution {
    public long minIncrease(int[] nums) {
        int n = nums.length;
        long[] ans = new long[2];
        boolean choose = false; 
        
        for (int i = 1; i < n - 1; i++) {
            int increase = Math.max(
                    0, 
                    Math.max(nums[i - 1], nums[i + 1]) - nums[i] + 1
                );

            if (ans[0] < ans[1]) {
                ans[1] = ans[0];
                choose = true;
            } else {
                if (choose) ans[1] += increase;
                choose = !choose;
            }

            if (i % 2 == 1) ans[0] += increase;
        }
        
        if (n % 2 == 1) return ans[0];
        return Math.min(ans[0], ans[1]);
    }
}
