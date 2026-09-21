class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; i++) tree[i] = new TreeNode(baseTime[i]);
        for (int[] edge : edges) {
            tree[edge[0]].children.add(tree[edge[1]]);
        }
        return search(tree[0]);
    }

    private long search(TreeNode node) {
        if (node.children.isEmpty()) return node.bTime;
        
        long earliest = Long.MAX_VALUE;
        long latest = 0;
        for (TreeNode child : node.children) {
            long finishTime = search(child);
            earliest = Math.min(earliest, finishTime);
            latest = Math.max(latest, finishTime);
        }
        return latest + latest - earliest + node.bTime;
    }
}

class TreeNode {
    int bTime;
    List<TreeNode> children;
    TreeNode(int bTime) {
        this.bTime = bTime;
        children = new ArrayList<>();
    } 
}
