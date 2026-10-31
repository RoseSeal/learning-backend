class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums1[i] - nums2[i]);
        }

        int last = nums2[n];
        int lastDiff = Math.abs(nums1[0] - last);
        for (int i = 0; i < n; i++) {
            if (last <= nums1[i] && last >= nums2[i] 
                || last >= nums1[i] && last <= nums2[i]
            ) {
                lastDiff = 0;
                break;
            }
            lastDiff = Math.min(lastDiff, 
                Math.min(
                    Math.abs(nums1[i] - last),
                    Math.abs(nums2[i] - last)
                )
            );
        }

        return ans + lastDiff + 1;
    }
}