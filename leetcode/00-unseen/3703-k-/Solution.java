class Solution {
    private int k;

    public String removeSubstring(String s, int k) {
        this.k = k;
        int idx = 0;
        Deque<int[]> stack = new ArrayDeque<>();

        while (idx < s.length()) {
            int[] curr = new int[2];
            while (idx < s.length() && s.charAt(idx) == '(') {
                curr[0]++;
                idx++;
            }
            int rightLen = 0;
            while (idx < s.length() && s.charAt(idx) == ')') {
                curr[1]++;
                idx++;
            }
            delete(curr);
            
            while (!stack.isEmpty() && tryLink(stack.peek(), curr)) {
                stack.pop();
                delete(curr);
            }
            stack.push(curr);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) append(sb, stack.pollLast());
        return sb.toString();
    }

    private boolean tryLink(int[] pre, int[] curr) {
        if (pre[1] == 0 || pre[1] != 0 && curr[0] == 0) {
            curr[0] += pre[0];
            curr[1] += pre[1];
            return true;
        }
        return false;
    }

    private void delete(int[] curr) {
        int d = Math.min(curr[0], curr[1]) / k * k;
        curr[0] -= d;
        curr[1] -= d;
    }

    private void append(StringBuilder sb, int[] curr) {
        for (int i = 0; i < curr[0]; i++) sb.append('(');
        for (int i = 0; i < curr[1]; i++) sb.append(')');
    }
}