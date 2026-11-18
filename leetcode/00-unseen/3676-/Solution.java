class Solution {
    public long bowlSubarrays(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            while (!stack.isEmpty() && curr > nums[stack.peek()]) {
                int j = stack.pop();
                if (i - j > 1) ans++;
            }
            
            int j = stack.isEmpty() ? n : stack.peek();
            if (i - j > 1) ans++;
            stack.push(i);
        }

        return ans;
    }
}