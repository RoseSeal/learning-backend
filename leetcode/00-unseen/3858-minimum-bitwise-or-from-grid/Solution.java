class Solution {
    public int minimumOR(int[][] grid) {
        int mask = 0; // 指定必须为0的bit位
        int miniORvalue = 0;

        for (int k = 16; k >= 0; k--) {
            int nextMask = mask | 1 << k;
            boolean valid = true;
            for (int i = 0; i < grid.length; i++) {
                boolean has0 = false;
                for (int j = 0; j < grid[0].length; j++) {
                    if ((grid[i][j] & nextMask) == 0) {
                        has0 = true;
                        break;
                    }
                }
                if (!has0) {
                    valid = false;
                    break;
                }
            }
            if (valid) mask = nextMask;
            else miniORvalue |= 1 << k;
        }

        return miniORvalue;
    }
}
