# 3738. 替换至多一个元素后最长非递减子数组

记录：独立 · 未超时 · 首交未过

## 我的思考

题意：给定整数数组 nums，最多把其中一个元素改成任意值，求最长非递减连续子数组的长度。

一次遍历，维护三个状态：
- `notChange`：以 i 结尾、不做修改的非递减长度。
- `changed`：以 i 结尾、已使用一次修改的非递减长度。
- `changedNum`：changed 序列在 i 处的末尾值，下一个元素必须 ≥ 它。

当 `curr < changedNum` 时，changed 序列无法延续，从 `notChange(i-1)` 重新开始并使用这次修改。修改有两种选法：
- 把 curr 抬高到 pre，末尾值为 pre。
- 把 pre 降低到 [prepre, curr] 之间，前提是 curr ≥ prepre，末尾值为 curr。

如果 pre 本身已经小于 prepre（以 i-1 结尾的段只有 pre 一个元素），末尾值取 `min(curr, pre)`。

修改窗口第一个元素的情况单独处理：答案取 `notChange + 1`，再用 `Math.min(ans, n)` 防止整个数组都非递减时返回 n + 1。

## 卡点

- 初版把 `else changedNum = curr` 误写成 `else changedNum = pre`，靠打 log 才定位，花了约 15 分钟。
- 末尾值一律取 pre，即默认只能"把 curr 抬高到 pre"。bug case `[-4,3,-4,-2]` 说明在条件满足时也可以把 pre 降低，这时末尾值是 curr。
- 漏了"修改窗口第一个元素"的情况。bug case `[3,-4,-2]` 说明修改窗口头部能得到更长的结果，长度一定是 `notChange + 1`。

## 关键点

- 被修改的元素在窗口里只有头、中、尾三种位置。头部修改对应 `notChange + 1`；中间或尾部修改对应"抬高 curr"或"降低 pre"。
- changed 序列的末尾值不一定是 pre，要在可行的修改方式里取最小的末尾值。

## 复杂度

时间 O(n)，空间 O(1)。

## 注意