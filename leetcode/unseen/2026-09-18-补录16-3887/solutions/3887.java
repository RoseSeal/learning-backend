class Solution {
    public int minRemovals(int[] nums, int target) {
        int size = getMax(nums);
        if (target >= size) return -1;
        int n = nums.length;
        int[] prev = new int[size];
        int[] curr = new int[size];

        Arrays.fill(prev, -1);
        Arrays.fill(curr, -1);
        prev[0] = 0;
        prev[nums[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < size; j++) {
                if (prev[j] >= 0) {
                    int xorValue = j ^ nums[i];
                    curr[xorValue] = Math.max(
                        prev[xorValue], prev[j] + 1
                    );
                    curr[j] = Math.max(prev[j], curr[j]);
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp; // 下一轮 curr[i] 至少被更新为 prev[i]，因此无需清空原始 prev 内容
        }

        if (prev[target] == -1) {
            return -1;
        } else return n - prev[target];
    }

    private int getMax(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int size = 1;
        while (max > 0) {
            max >>= 1;
            size <<= 1;
        }
        return size;
    }
}
