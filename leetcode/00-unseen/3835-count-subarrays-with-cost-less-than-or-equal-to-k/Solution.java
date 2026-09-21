class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;

        long ans = 0;
        int left = 0;
        int right = 0;

        Deque<Integer> maxStack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();

        while (right < n) {
            // 拓展
            int curr = nums[right];
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= curr) maxStack.pop();
            maxStack.push(right);
            while (!minStack.isEmpty() && nums[minStack.peek()] >= curr) minStack.pop();
            minStack.push(right);

            // 收缩
            while ((long)(right - left + 1) * 
                (nums[maxStack.peekLast()] - nums[minStack.peekLast()]) > k
            ) {
                if (maxStack.peekLast() == left) maxStack.pollLast();
                if (minStack.peekLast() == left) minStack.pollLast();
                left++;
            }

            // 收集答案
            ans += right - left + 1;

            // 推进指针
            right++;
        }

        return ans;
    }
}