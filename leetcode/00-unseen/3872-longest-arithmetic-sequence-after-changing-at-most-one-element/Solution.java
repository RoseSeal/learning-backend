class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        int ans = 2;

        int i = 1;                                  
        while (i < n) {
            int s = i - 1;                           
            int diff = nums[i] - nums[i - 1];

            while (i < n && nums[i] - nums[i - 1] == diff) i++;
            int len = i - s;                         

            ans = Math.max(ans, Math.min(len + 1, n));  
            if (i + 1 < n && nums[i + 1] - nums[i - 1] == diff * 2) {
                int j = i + 2;                       
                while (j < n && nums[j] - nums[j - 1] == diff) j++;
                ans = Math.max(ans, j - s);          
            }
            if (s >= 2 && nums[s] - nums[s - 2] == diff * 2) {
                ans = Math.max(ans, len + 2);
            }
        }
        return ans;
    }
}
