class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] buffs = new long[n];
        for (int[] boost : boosts) {
            int boostNum = boost[2];
            if (boost[0] > 0) buffs[boost[0] - 1] -= boostNum;
            buffs[boost[1]] += boostNum;
        }
        long currBuff = 0;
        long ans = 0;
        int idx = n - 1;
        for (; idx >= 0; idx--) {
            currBuff += buffs[idx];
            if (monsters[idx] > currBuff) {
                break;
            }
        }
        if (idx >= 0) ans += monsters[idx--] - currBuff;
        for (; idx >= 0; idx--) {
            ans += monsters[idx];
        }
        return ans;
    }
}
