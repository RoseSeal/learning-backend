class ExamTracker {

    private List<State> recorder;

    public ExamTracker() {
        recorder = new ArrayList<>();
        recorder.add(new State(0, 0));
    }
    
    public void record(int time, int score) {
        long preSumScore = recorder.get(recorder.size() - 1).preSumScore + score;
        recorder.add(new State(time, preSumScore));
    }
    
    public long totalScore(int startTime, int endTime) {
        return search(endTime) - search(startTime - 1);
    }

    // 二分查找 <= time 的最后一个时间点的前缀和值
    private long search(int time) {
        int begin = 0;
        int end = recorder.size() - 1;
        while (begin < end) {
            int mid = begin + (end - begin + 1) / 2;
            int midTime = recorder.get(mid).time;
            if (midTime > time) end = mid - 1;
            else begin = mid;
        }

        return recorder.get(begin).preSumScore;
    }
}

class State {
    int time;
    long preSumScore;
    
    public State(int time, long preSumScore) {
        this.time = time;
        this.preSumScore = preSumScore;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */