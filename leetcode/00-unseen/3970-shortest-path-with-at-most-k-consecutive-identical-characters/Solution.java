class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        int[][] dist = new int[n][k];
        for (int i = 1; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = 
            new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currSame = curr[1];
            int currDist = curr[2];
            if (dist[currNode][currSame] < currDist) continue;
            for (int j = currSame + 1; j < k && dist[currNode][j] > currDist; j++) {
                dist[currNode][j] = currDist;
            }
            for (int[] edge : graph[currNode]) {
                int nextNode = edge[0];
                int nextSame = 
                    labels.charAt(nextNode) == labels.charAt(currNode) ? 
                    currSame + 1 : 0;
                if (nextSame >= k) continue;
                int nextDist = currDist + edge[1];
                if (nextDist < dist[nextNode][nextSame]) {
                    pq.offer(new int[]{nextNode, nextSame, nextDist});
                    dist[nextNode][nextSame] = nextDist;
                }
            }
        }

        return dist[n - 1][k - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1][k - 1];
    }
}
