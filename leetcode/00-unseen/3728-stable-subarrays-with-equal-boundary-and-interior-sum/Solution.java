class Solution {
    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + capacity[i];
        }

        int[] nextSame = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            int curr = capacity[i];
            nextSame[i] = map.getOrDefault(curr, -1);
            map.put(curr, i);
        }

        long ans = 0;
        for (Integer idx : map.values()) {
            Map<Long, Long> preSums = new HashMap<>();
            Long wait = null;
            while (idx != -1) {
                long currPreSum = preSum[idx];
                int value = capacity[idx];
                ans += preSums.getOrDefault(currPreSum - value * 2, 0L);
                
                if (wait != null) {
                    preSums.merge(wait, 1L, Long::sum);
                    wait = null;
                }
                int next = nextSame[idx];
                if (next - idx < 2) wait = currPreSum;
                else preSums.merge(currPreSum, 1L, Long::sum);

                idx = next;
            }
        }

        return ans;
    }
}