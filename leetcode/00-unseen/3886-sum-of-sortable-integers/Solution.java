class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) factors.add(i);
        }

        int[] nextDec = new int[n];
        nextDec[n - 1] = n;
        int decIdx = n;
        for (int i = n - 1; i > 0; i--) {
            nextDec[i] = decIdx;
            if (nums[i - 1] > nums[i]) decIdx = i;
            
        }
        nextDec[0] = decIdx;

        int[] preGreater = new int[n];
        int great = nums[0];
        for (int i = 1; i < n; i++) {
            preGreater[i] = great;
            great = Math.max(great, nums[i]);
        }
                
        int ans = 0;
        for(int factor : factors) {
            boolean isAns = true;

            for (int begin = 0; begin < n; begin += factor) {
                int currDecIdx = nextDec[begin];
                int end = begin + factor - 1;
                boolean hasDec = currDecIdx <= end;
                int minNum = hasDec ? nums[currDecIdx] : nums[begin];
                
                if (hasDec && (nextDec[currDecIdx] <= end || nums[end] > nums[begin])
                        || minNum < preGreater[begin]
                ) {
                    isAns = false;
                    break;
                }
            }

            if (isAns) ans += factor;
        }

        return ans;
    }
}
