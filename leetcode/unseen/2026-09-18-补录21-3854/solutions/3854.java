class Solution {
    public int[] makeParityAlternating(int[] nums) {
        int n = nums.length;
        if(n == 1) return new int[]{0, 0};
        // idx = 0 为 nums[0] 是偶数时的操作次数，idx = 1 为 nums[0] 是奇数操作次数
        int[] candidate = new int[2];
        int[] minPossibleMax = new int[2];
        int[] maxPossibleMin = new int[2];
        minPossibleMax[0] = minPossibleMax[1] = Integer.MIN_VALUE;
        maxPossibleMin[0] = maxPossibleMin[1] = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if ((i & 1) != (num & 1)) {
                candidate[0]++;
                minPossibleMax[0] = Math.max(minPossibleMax[0], num - 1);
                maxPossibleMin[0] = Math.min(maxPossibleMin[0], num + 1);
                minPossibleMax[1] = Math.max(minPossibleMax[1], num);
                maxPossibleMin[1] = Math.min(maxPossibleMin[1], num);
            }
            else {
                candidate[1]++;
                minPossibleMax[0] = Math.max(minPossibleMax[0], num);
                maxPossibleMin[0] = Math.min(maxPossibleMin[0], num);
                minPossibleMax[1] = Math.max(minPossibleMax[1], num - 1);
                maxPossibleMin[1] = Math.min(maxPossibleMin[1], num + 1);
            }
        }

        int[] diff = new int[2];
        diff[0] = minPossibleMax[0] - maxPossibleMin[0];
        diff[1] = minPossibleMax[1] - maxPossibleMin[1];

        int[] ans = new int[2];
        if (candidate[0] < candidate[1] ||
        candidate[0] == candidate[1] && diff[0] <= diff[1]
        ) {
            ans[0] = candidate[0];
            ans[1] = diff[0];
        } else {
            ans[0] = candidate[1];
            ans[1] = diff[1];
        }
        ans[1] = Math.max(ans[1], 1);
        return ans;
    }
}
