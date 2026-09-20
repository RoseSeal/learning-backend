class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        if (n == 2) return -1;
        
        char first = s.charAt(0);
        boolean firstIsMin = true;
        boolean firstIsMax = true;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < first) firstIsMin = false;
            if (c >= first) firstIsMax = false;
        }
        char last = s.charAt(n - 1);
        boolean lastIsMax = true;
        boolean lastIsMin = true;
        for (int i = 0; i < n - 1; i++) {
            char c = s.charAt(i);
            if (c > last) lastIsMax = false;
            if (c <= last) lastIsMin = false;
        }

        if (firstIsMin || lastIsMax) return 1;
        if (firstIsMax && lastIsMin) return 3;
        else return 2;
    }
}
