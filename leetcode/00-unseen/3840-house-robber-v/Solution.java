class Solution {
    public long rob(int[] nums, int[] colors) {
        long rob = nums[0];
        long skip = 0;
        for (int i = 1; i < nums.length; i++) {
            boolean isSameColor = colors[i] == colors[i - 1]; // nums.length == colors.length
            int curr = nums[i];

            long nextRob = Math.max(skip + curr, rob + (isSameColor ? 0 : curr));
            long nextSkip = Math.max(skip, rob);

            rob = nextRob;
            skip = nextSkip;
        }

        return Math.max(rob, skip);
    }
}