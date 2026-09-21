class Solution {
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;

        int valueMask = 0;
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            valueMask <<= 1;
            valueMask += num;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            graph[i].add(j);
            graph[j].add(i);
        }

        int ans = 0;
        for (int mask = 1; mask < (1 << n); mask++) { 
            if (Integer.bitCount(mask & valueMask) % 2 != 0) continue;
            if (isConnected(mask, graph)) ans++;
        }

        return ans;
    }

    private boolean isConnected(int mask, List<Integer>[] graph) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) continue; 
            for (int j : graph[i]) {
                if ((mask & (1 << j)) == 0) continue;
                uf.union(i, j);
            }
        }

        int parent = -1;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) continue;
            if (parent == -1) parent = uf.find(i);
            if (parent != uf.find(i)) return false;
        }

        return true;
    }
}

class UnionFind {
    private int[] parent;
    private int[] size;
    UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        return parent[i] = parent[i] == i ? i : find(parent[i]);
    }

    public boolean union(int i, int j) {
        int pI = find(i);
        int pJ = find(j);
        if (pI == pJ) return false;
        if (size[pI] < size[pJ]) {
            int temp = pJ;
            pJ = pI;
            pI = temp;
        }
        parent[pJ] = pI;
        size[pI] += size[pJ];
        return true;
    }
}
