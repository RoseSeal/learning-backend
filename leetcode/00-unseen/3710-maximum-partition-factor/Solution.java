class Solution {
    public int maxPartitionFactor(int[][] points) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int[] pi = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] pj = points[j];
                int dist = Math.abs(pi[0] - pj[0]) + Math.abs(pi[1] - pj[1]);
                map.computeIfAbsent(dist, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        PriorityQueue<DistToPair> pq = new PriorityQueue<>((a, b) -> (a.dist - b.dist));
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            DistToPair dists = new DistToPair(entry.getKey(), entry.getValue());
            pq.offer(dists);
        }

        PairRels pairRels = new PairRels(n);
        while (!pq.isEmpty()) {
            DistToPair curr = pq.poll();
            for (int[] pair : curr.points) {
                int i = pair[0];
                int j = pair[1];
                if (!pairRels.union(i, j)) return curr.dist;
            }
        }

        return 0;
    }
}

class DistToPair {
    int dist;
    List<int[]> points;
    
    public DistToPair(int dist, List<int[]> points) {
        this.dist = dist;
        this.points = points;
    }
}

class PairRels {
    private static final int ODD = 1;
    private static final int EVEN = 2;
    private static final int NONE = -1;
    private int[][] rels; 
    private int n;

    public PairRels(int n) {
        this.n = n;
        this.rels = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(rels[i], NONE);
            rels[i][i] = EVEN;
        }
    }

    public boolean union(int i, int j) {
        if (rels[i][j] == EVEN) return false;
        if (rels[i][j] == ODD) return true;
        List<Integer> compI = new ArrayList<>();
        for (int x = 0; x < n; x++) if (rels[i][x] != NONE) compI.add(x);
        List<Integer> compJ = new ArrayList<>();
        for (int y = 0; y < n; y++) if (rels[j][y] != NONE) compJ.add(y);
        
        for (int x : compI) {
            int relix = rels[i][x];
            rels[j][x] = relix ^ 1;
            rels[x][j] = relix ^ 1;
            for (int y : compJ) {
                int reljy = rels[j][y];
                rels[x][y] = relix ^ 1 ^ reljy;
                rels[y][x] = relix ^ 1 ^ reljy;
            }
        }

        return true;
    }
}