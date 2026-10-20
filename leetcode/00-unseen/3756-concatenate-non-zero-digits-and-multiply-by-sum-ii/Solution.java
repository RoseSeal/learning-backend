class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] preSum = new int[n + 1];     // preSum[i]：前 i 个字符的数字和
        int[] cnt = new int[n + 1];        // cnt[i]：前 i 个字符中非零数字的个数
        long[] prefix = new long[n + 1];   // prefix[k]：前 k 个非零数字拼成的数 mod MOD
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - '0';
            preSum[i + 1] = preSum[i] + c;
            pow10[i + 1] = pow10[i] * 10 % MOD;
            cnt[i + 1] = cnt[i];
            if (c != 0) {
                prefix[cnt[i] + 1] = (prefix[cnt[i]] * 10 + c) % MOD;
                cnt[i + 1]++;
            }
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            long sum = preSum[r + 1] - preSum[l];
            int pl = cnt[l], pr = cnt[r + 1];
            long x = (prefix[pr] - prefix[pl] * pow10[pr - pl] % MOD + MOD) % MOD;
            ans[i] = (int) (sum % MOD * x % MOD);
        }
        return ans;
    }
}