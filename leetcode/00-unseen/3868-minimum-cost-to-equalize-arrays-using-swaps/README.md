# 3868. Minimum Cost to Equalize Arrays Using Swaps

记录：独立 · 未标超时 · 首交未记录（迁移自旧 unseen 记录）

## 题意

两个长度为 n 的数组。同一数组内交换两个元素免费；交换 nums1[i] 与 nums2[i] 花费 1。求使两个数组相同（作为多重集）的最小花费，不可能返回 -1。约束：n、值 ≤ 8e4。

## 我的思考

我确定balance必然为0，nums1多，nums2就必然少，这是因为nums1和nums2长度相等。
这题很简单，一共只花了10min，应该是mid里偏ez的一档

## 卡点

## 关键点

- 两数组长度相等，nums1 多出的值 nums2 必然少，差值计数总和（balance）必为 0。

## 主流解对照

## 复杂度

（补）O(n + V)，空间 O(V)。

## 注意
