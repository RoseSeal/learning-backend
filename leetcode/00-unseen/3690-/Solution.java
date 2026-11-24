class Solution {
    private static final int MAX_MASK = 555556;
    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nums2[i], i);
        int target = getMask(nums2, map);
        int begin = getMask(nums1, map);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(begin);
        int dist = 0;
        boolean[] visited = new boolean[MAX_MASK];
        visited[begin] = true;
        int[] currArr = new int[n];
        int[] nextArr = new int[n];
        while (!queue.isEmpty()) {
            int qLen = queue.size();
            for (int idx = 0; idx < qLen; idx++) {
                int curr = queue.poll();
                if (curr == target) return dist;
                for (int i = n - 1; i >= 0; i--) {
                    currArr[i] = curr % 10;
                    curr /= 10;
                }
                for (int segStart = 0; segStart < n; segStart++) {
                    for (int len = 1; len + segStart <= n; len++) {
                        int segEnd = segStart + len - 1;
                        for (int insertIdx = segEnd + 1; insertIdx <= n; insertIdx++) {
                            int j = 0;
                            for (int i = 0; i < segStart; i++) nextArr[j++] = currArr[i];
                            for (int i = segEnd + 1; i < insertIdx; i++) nextArr[j++] = currArr[i];
                            for (int i = segStart; i <= segEnd; i++) nextArr[j++] = currArr[i];
                            for (int i = insertIdx; i < n; i++) nextArr[j++] = currArr[i];
                            int next = 0;
                            for (int num : nextArr) next = next * 10 + num;
                            if (visited[next]) continue;
                            visited[next] = true;
                            queue.offer(next);
                        }                
                    }
                }
            }
            dist++;  
        }
        return -1;
    }

    private int getMask(int[] nums, Map<Integer, Integer> map) {
        int mask = 0;
        for (int num : nums) mask = mask * 10 + map.get(num);
        return mask;
    }
}