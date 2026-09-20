class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int m = shifts.length;
        TreeMap<Long, Integer> map = new TreeMap<>();
        long tasksTimeSum = 0;
        map.put(0L, 0);
        for (int i = 0; i < n; i++) {
            tasksTimeSum += tasks[i];
            map.put(tasksTimeSum, i + 1);
        }
        long finishedTime = 0;
        int[] restTasks = new int[m];
        for (int i = 0; i < m; i++) {
            long shift = shifts[i] + finishedTime;
            restTasks[i] = n - map.floorEntry(shift).getValue();
            if (restTasks[i] == 0) finishedTime = 0;
            else finishedTime = shift;
        }
        return restTasks;
    }
}
