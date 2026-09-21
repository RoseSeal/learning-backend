# 3919. Minimum Cost to Move Between Indices

记录：独立 · 未标超时 · 首交未记录（迁移自旧 unseen 记录）

## 题意

严格递增数组 nums。closest(x) 为与 nums[x] 差值最小的相邻下标（平局取较小下标）。从任意下标出发，可以花 |nums[x] - nums[y]| 移到任意 y，或花 1 移到 closest(x)。多组查询 [l, r]，求从 l 到 r 的最小代价。约束：n、查询数 ≤ 1e5。

## 我的思考

耗时 20min。

## 卡点

## 关键点

## 主流解对照

## 复杂度

（补）预处理 O(n)，每次查询 O(1)；空间 O(n)。

## 注意
