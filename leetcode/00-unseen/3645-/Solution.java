class Solution {
    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        PriorityQueue<Integer>[] limits = new PriorityQueue[n + 1];
        long ans = 0;
        for (int i = 0; i <= n; i++) limits[i] = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            int lim = limit[i];
            PriorityQueue<Integer> curr = limits[lim];
            ans += value[i];
            curr.add(value[i]);
            if (curr.size() > lim) ans -= curr.poll();
        }

        return ans;
    }
}