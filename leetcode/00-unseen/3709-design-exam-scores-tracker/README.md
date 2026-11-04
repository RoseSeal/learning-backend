# 3709. Design Exam Scores Tracker

记录：独立 · 未超时 · 首交通过

## 我的思考
题意：设计一个类，按严格递增的时间顺序记录考试分数，并查询闭区间 [startTime, endTime] 内的总分。查询不会超出最近一次记录的时间。约束：time、score ≤ 1e9，总调用次数 ≤ 1e5。

做法：time 严格递增，所以直接追加记录，列表天然有序，同时维护前缀和（prefix sum）。查询时二分找 time ≤ x 的最后一条记录，答案 = f(endTime) − f(startTime − 1)。开头放一个哨兵 (0, 0)：time ≥ 1 保证二分一定能落到这条记录上，因此不用处理「找不到」的情况。
13min完成。

## 卡点

## 关键点

## 复杂度
record O(1)，totalScore O(log n)，空间 O(n)。

## 注意
前缀和最大约 1e14，要用 long。