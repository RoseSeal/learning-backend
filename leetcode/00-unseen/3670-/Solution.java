class Solution {
    public long maxProduct(int[] nums) {
        int d = 1;
        for (int num : nums) {
            num >>= d;
            while (num > 0) {
                num >>= 1;
                d++;
            }
        }

        // map[x] 为nums中满足x中为1比特位对应位为0的最大值
        int[] map = new int[1 << d]; 
        int full = (1 << d) - 1;
        for (int num : nums) {
            map[full ^ num] = num;
        }
        for (int x = full; x >= 0; x--) {
            for (int i = 1; i < (1 << d); i <<= 1) {
                if ((x & i) != 0) continue;
                map[x] = Math.max(
                    map[x],
                    map[x | i]
                );
            }
        }

        long ans = 0;
        for (int num : nums) {
            ans = Math.max(
                ans, 
                1L * num * map[num]
            );
        }

        return ans;
    }
}