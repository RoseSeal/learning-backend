class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            int out = e[0];
            int in = e[1];
            int w = e[2];
            graph[in].add(new int[]{out, w * 2});
            graph[out].add(new int[]{in, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int v = curr[0];
            int w = curr[1];
            if (w > dist[v]) continue;
            if (v == n - 1) break;
            for (int[] next : graph[v]) {
                int nv = next[0];
                int nw = next[1] + w;
                if (nw >= dist[nv]) continue;
                dist[nv] = nw;
                pq.offer(new int[]{nv, nw});
            }
        }

        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }
}