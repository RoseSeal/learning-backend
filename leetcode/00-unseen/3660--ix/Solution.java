class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] preMax = new int[n];
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            maxIdx = nums[maxIdx] > nums[i] ? maxIdx : i;
            preMax[i] = maxIdx;
        }
        int[] sufFarMin = buildSufFarMin(nums);

        int[] ans = new int[n];
        ans[n - 1] = nums[preMax[n - 1]];
        for (int i = n - 2; i >= 0; i--) {
            int k = sufFarMin[preMax[i]];
            ans[i] = i == k ? nums[preMax[k]] : ans[k];
        }

        return ans;
    }

    private int[] buildSufFarMin(int[] nums) {
        int n = nums.length;
        int[] sufFarMin = new int[n];
        int[] sufMin = new int[n];

        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(nums[i], sufMin[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            sufFarMin[i] = i;
            int l = i + 1, r = n - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (sufMin[mid] < nums[i]) {
                    sufFarMin[i] = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return sufFarMin;
    }
}