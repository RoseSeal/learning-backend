class Solution {
    public String maximumXor(String s, String t) {
        int n = s.length();
        int[] count = new int[2];
        for (int i = 0; i < n; i++) {
            if (t.charAt(i) == '0') count[0]++;
            else count[1]++;
        } 
        
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < n; idx++) {
            int curr = s.charAt(idx) - '0';
            if (count[curr ^ 1] > 0) {
                count[curr ^ 1]--;
                sb.append('1');
            } else {
                count[curr]--;
                sb.append('0');
            }
        }

        return sb.toString();
    }
}