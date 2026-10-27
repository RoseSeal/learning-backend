class Solution {
    public long minimumTime(int[] d, int[] r) {
        long lcm = lcm(r[0], r[1]);
        long begin = 0;
        long end = ((long)d[0] + d[1]) * 2;
        while (begin < end) {
            long mid = begin + (end - begin) / 2;
            if (!finished(mid, lcm, d, r)) begin = mid + 1;
            else end = mid;
        }

        return begin;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (long)a / gcd(a, b) * b;
    }

    private boolean finished(long end, long lcm, int[] d, int[] r) {
        return end - end / r[0] >= d[0]
            && end - end / r[1] >= d[1]
            && end - end / lcm >= (long)d[0] + d[1];
    }
}