class Solution {
    private int n;
    private static final int SENTINEL = Integer.MIN_VALUE;

    public int longestBalanced(String s) {
        n = s.length();
        int noLimitAns = noLimitLongest(s);
        int limitOneAns = Math.max(
            limitOneLongest(s, 'a'),
            Math.max(
                limitOneLongest(s, 'b'), 
                limitOneLongest(s, 'c')
            )
        );
        int limitTwoAns = limitTwoLongest(s);

        return Math.max(noLimitAns, 
            Math.max(limitOneAns, limitTwoAns)
        );
    }

    private int noLimitLongest(String s) {
        int ans = 0;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') countA++;
            if (c == 'b') countB++;
            if (c == 'c') countC++;
            long key = getKeyWithThree(countA, countB, countC);
            int preIdx = map.getOrDefault(key, SENTINEL);
            if (preIdx != SENTINEL) ans = Math.max(ans, i - preIdx);
            else map.put(key, i);
        }

        return ans;
    }

    private int limitOneLongest(String s, char limitC) {
        int ans = 0;
        char one = limitC == 'a' ? 'b' : 'a';
        char two = limitC == 'c' ? 'b' : 'c';
        int countOne = 0;
        int countTwo = 0;
        int[] map = new int[2 * n + 1];
        Arrays.fill(map, SENTINEL);
        map[n] = -1;
        int lastInvalidIdx = -1;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == limitC) {
                countOne = 0;
                countTwo = 0;
                map[n] = i;
                lastInvalidIdx = i;
                continue;
            }
            if (c == one) countOne++;
            if (c == two) countTwo++;
            int key = countOne - countTwo + n;
            if (map[key] >= lastInvalidIdx) ans = Math.max(ans, i - map[key]);
            else map[key] = i;
        }

        return ans;
    }

    private int limitTwoLongest(String s) {
        int ans = 1;
        int curr = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) curr++;
            else curr = 1;
            ans = Math.max(ans, curr);
        }

        return ans;
    }
    
    private long getKeyWithThree(int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        a -= min;
        b -= min;
        c -= min;
        return ((long) a * (n + 1) + b) * (n + 1) + c;
    }
}