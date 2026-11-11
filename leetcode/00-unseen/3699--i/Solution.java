class Solution {
    private final static int MOD = 1_000_000_007;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int[] up = new int[m];
        int[] down = new int[m];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        up[0] = 0;
        down[m - 1] = 0;
        countPreSum(up);
        countPreSum(down);

        for (int i = 0; i < n - 1; i++) {
            int[] nextUp = new int[m];
            int[] nextDown = new int[m];
            for (int x = 1; x < m; x++) {
                nextUp[x] = down[x - 1];
            }
            for (int x = 0; x < m - 1; x++) {
                nextDown[x] = (up[m - 1] - up[x] + MOD) % MOD; 
            }
            countPreSum(nextUp);
            countPreSum(nextDown);
            up = nextUp;
            down = nextDown;
        }

        return (up[m - 1] + down[m - 1]) % MOD;
    }

    private void countPreSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) nums[i] = (nums[i] + nums[i - 1]) % MOD;
    }
}