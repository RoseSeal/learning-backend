class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 1;
        int notChange = 1;
        int changed = 1;
        int changedNum = nums[0];
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            int pre = nums[i - 1];
            if (curr < changedNum) {
                changed = notChange;
                int prepre = i - 2 >= 0 ? nums[i - 2] : Integer.MIN_VALUE;
                changedNum = pre < prepre ? Math.min(curr, pre) :
                            (curr < prepre ? pre : curr);
            } else changedNum = curr;
            if (curr < pre) notChange = 0;
            
            notChange++;
            changed++;
            ans = Math.max(ans, notChange + 1);
            ans = Math.max(ans, changed);
        }

        return Math.min(ans, n);
    }
}