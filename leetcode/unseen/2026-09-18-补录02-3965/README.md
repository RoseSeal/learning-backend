# 补录02-3965 — Unseen 2026-09-18

<!-- unseen-meta:start -->
日期：2026-09-18
来源：周赛185
场次：补录02-3965
题目：3965
结果：
反馈：
耗时：
<!-- unseen-meta:end -->

## 复盘笔记

# 3965

## 题意

以 0 为根的任务树，每个任务有 baseTime。叶子完成时间为 baseTime；非叶子取子节点完成时间的最小值 earliest、最大值 latest，完成时间为 latest + (latest - earliest) + baseTime。求根的完成时间。

## 我的思考

耗时 13min。

## 卡点


## 关键点


## 主流解对照


## 复杂度

（补）时间 O(n)，空间 O(n)（递归深度最坏 O(n)）。

## 注意
