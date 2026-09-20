class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) factors.add(i);
        }

        int ans = 0;
        for (int len : factors) {
            boolean isAns = true;
            int great = 0;

            for (int begin = 0; begin < n; begin += len) {
                boolean hasDecrease = false;
                int end = begin + len;
                int pre = 0;
                int beginNum = nums[begin];
                int currGreat = great;

                for (int i = begin; i < end; i++) {
                    int num = nums[i];
                    currGreat = Math.max(currGreat, num);

                    if (num < pre && hasDecrease == false) {
                        hasDecrease = true;
                        pre = 0;
                    }

                    if (hasDecrease && (num < pre || num > beginNum) || 
                        num < great
                    ) {
                        isAns = false;
                        break;
                    }

                    pre = num;
                }

                great = currGreat;
                if (!isAns) break;
            }

            if (isAns) ans += len;
        }

        return ans;
    }
}
