class Solution {
    public int minOperations(String s1, String s2) {
        int n = s1.length();
        if (n == 1 && s2.charAt(0) == '0' && s1.charAt(0) == '1') return -1;
        int ans = 0;
        boolean reverse = false;
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (reverse) {
                c1 = '0';
                reverse = false;
            }
            if (c1 == c2) continue;
            ans++;
            if (c2 == '0') {
                if (i + 1 < n && s1.charAt(i + 1) == '1') {
                    reverse = true;
                } else ans ++;
            }
        }
        return ans;
    }
}
