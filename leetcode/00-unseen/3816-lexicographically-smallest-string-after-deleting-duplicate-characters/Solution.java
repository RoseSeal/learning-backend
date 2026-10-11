class Solution {
    public String lexSmallestAfterDeletion(String s) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length() ? s.charAt(i) : 'a' - 1;
            while (!sb.isEmpty()) {
                int topIdx = sb.length() - 1;
                char topC = sb.charAt(topIdx);
                if (count[topC - 'a'] > 1 && c < topC) {
                    count[topC - 'a']--;
                    sb.deleteCharAt(topIdx);
                } else break;
            }
            sb.append(c);
        }
        sb.deleteCharAt(sb.length() - 1);
        
        return sb.toString();
    }
}