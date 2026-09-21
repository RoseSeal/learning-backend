class Solution {
    public int minGenerations(int[][] points, int[] target) {
        int n = points.length;
        int targetSetValue = getSetValue(target);
        Set<Integer> pointSet = new HashSet<>();
        List<int[]> pointsList = new ArrayList<>();
        for (int[] point : points) {
            int setValue = getSetValue(point);
            if (setValue == targetSetValue) return 0;
            pointsList.add(point);
            pointSet.add(setValue);
        }
        if (n == 1) return -1;

        int k = 1;
        int currGeneIdx = 0;
        while (true) {
            int size = pointsList.size();
            boolean stop = true;
            for (int i = currGeneIdx; i < size; i++) {
                int[] currPoint = pointsList.get(i);
                for (int j = 0; j < i; j++) {
                    int[] prevPoint = pointsList.get(j);
                    int[] newPoint = new int[]{
                        (currPoint[0] + prevPoint[0]) / 2,
                        (currPoint[1] + prevPoint[1]) / 2,
                        (currPoint[2] + prevPoint[2]) / 2,
                    };
                    int setValue = getSetValue(newPoint);
                    if (pointSet.contains(setValue)) continue;
                    if (setValue == targetSetValue) return k;
                    pointSet.add(setValue);
                    pointsList.add(newPoint);
                    stop = false;
                }
            }
            currGeneIdx = size;
            k++;
            if (stop) return -1;
        }

    }

    private int getSetValue(int[] a) {
        int setValue = 0;
        for (int num : a) {
            setValue *= 10;
            setValue += num;
        }
        return setValue;
    }
}
