class Solution {
    private final static int SIZE = 80001;
    public int minCost(int[] nums1, int[] nums2) {
        int n = nums1.length; // 题目保证nums1.length == nums2.length
        int[] diffCount = new int[SIZE];
        for(int i = 0; i < n; i++) {
            diffCount[nums1[i]]++;
            diffCount[nums2[i]]--;
        }
        
        int ans = 0;
        for (int diff : diffCount) {
            if (diff % 2 != 0) return -1;
            if (diff > 0) ans += diff / 2;
        }

        return ans;
    }
}
