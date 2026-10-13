class Solution {
    public long maximumScore(int[] nums, String s) {
        int n = nums.length;
        long score = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );

        for (int i = 0; i < n; i++) {
            heap.offer(nums[i]);
            if (s.charAt(i) == '1') {
                score += heap.poll();
            }
        }

        return score;
    }
}