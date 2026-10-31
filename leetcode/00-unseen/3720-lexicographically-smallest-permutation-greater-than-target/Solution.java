class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        int biggerIdx = search(Arrays.copyOf(count, count.length), target);
        return biggerIdx == -1 ? "" : buildAns(biggerIdx, count, target);
    }

    private int search(int[] count, String target) {
        int biggerIdx = -1;
        for (int i = 0; i < target.length(); i++) {
            int tCidx = target.charAt(i) - 'a';
            for (int j = tCidx + 1; j < 26; j++) {
                if (count[j] > 0) {
                    biggerIdx = i;
                    break;
                }
            }
            count[tCidx]--;
            if (count[tCidx] < 0) break;
        }
        return biggerIdx;
    }


    private String buildAns(int biggerIdx, int[] count, String target) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < biggerIdx; i++) {
            char c = target.charAt(i);
            sb.append(c);
            count[c - 'a']--;
        }

        for (int i = target.charAt(biggerIdx) - 'a' + 1; i < 26; i++) {
            if (count[i] > 0) {
                sb.append((char) ('a' + i));
                count[i]--;
                break;
            }
        }

        for (int i = 0; i < 26; i++) {
            while (count[i]-- > 0) {
                sb.append((char) ('a' + i));
            }
        }

        return sb.toString();
    }
}