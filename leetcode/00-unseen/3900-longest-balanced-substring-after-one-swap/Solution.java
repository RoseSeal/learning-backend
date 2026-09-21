class Solution {
    public int longestBalanced(String s) {
        int ans = 0;
        int n = s.length();
        int[] any = new int[2 * (n + 2) + 1];
        int[] has0 = new int[2 * (n + 2) + 1];
        int[] has1 = new int[2 * (n + 2) + 1];
        Arrays.fill(any, n);
        Arrays.fill(has0, n);
        Arrays.fill(has1, n);
        any[n + 2] = -1;
        int diff = n + 2;

        int[] leftIdx = new int[2];
        leftIdx[0] = leftIdx[1] = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1' && leftIdx[1] == n) leftIdx[1] = i;
            else if (s.charAt(i) == '0' && leftIdx[0] == n)leftIdx[0] = i;
            else if (leftIdx[1] != n && leftIdx[0] != n) break;
        }
        int[] rightIdx = new int[2];
        rightIdx[0] = rightIdx[1] = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1' && rightIdx[1] == -1) rightIdx[1] = i;
            else if (s.charAt(i) == '0' && rightIdx[0] == -1)rightIdx[0] = i;
            else if (rightIdx[1] != -1 && rightIdx[0] != -1) break;
        }

        for (int right = 0; right < n; right++) {
            diff += s.charAt(right) == '1' ? 1 : -1;

            int left = right;
            int candidate = any[diff];
            // 相等
            left = Math.min(left, candidate);
            // 换一个0进来
            candidate = 
                right < rightIdx[0] ? 
                any[diff - 2] : 
                has0[diff - 2];
            left = Math.min(left, candidate);
            // 换一个1进来
            candidate = 
                right < rightIdx[1] ? 
                any[diff + 2] : 
                has1[diff + 2];
            left = Math.min(left, candidate);

            // 更新答案
            ans = Math.max(ans, right - left);
            
            // 更新表
            any[diff] = Math.min(any[diff], right);
            if (right > leftIdx[0]) has0[diff] = Math.min(has0[diff], right);
            if (right > leftIdx[1]) has1[diff] = Math.min(has1[diff], right);
        }

        return ans;
    }
}
