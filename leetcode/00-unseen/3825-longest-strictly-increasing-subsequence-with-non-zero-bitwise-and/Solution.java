class Solution {
    public int longestSubsequence(int[] nums) {
        int ans = 0;
        
        for (int i = 0; i <= 30; i++) {
            int mask = 1 << i;
            ans = Math.max(ans, longestWithMask(nums, mask));
        }
        
        return ans;
    }

    private int longestWithMask(int[] nums, int mask) {
        int n = nums.length;
        int[] tail = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if ((num & mask) == 0) continue;
            int pos = binarySearch(tail, len, num);
            tail[pos] = num;
            if (pos == len) len++;
        }
        return len;
    }

    private int binarySearch(int[] nums, int end, int target) {
        int left = 0;
        int right = end;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target) return mid;
            
            if (num > target) right = mid;
            else left = mid + 1;
        }

        return right;
    }
}