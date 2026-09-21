# 3835. Count Subarrays With Cost Less Than or Equal to K

记录：独立 · 未超时 · 首交未过

## 我的思考

题意：给定整数数组 `nums` 和整数 `k`，统计满足 `(max - min) × 长度 ≤ k` 的子数组数量。

约束：`1 <= n <= 10^5`，`1 <= nums[i] <= 10^9`，`0 <= k <= 10^15`。

- n = 10^5，O(n²) 的暴力枚举不可行。
- 把 `(max - min) × len` 记为子数组的 cost。子数组扩张时，max 只增不减，min 只减不增，长度只增，所以 cost 单调不减；收缩时则单调不增。
- 因此可以用滑动窗口：右端点每次扩张一格，cost > k 时收缩左端点，然后累加 `right - left + 1`。
- 用两个单调双端队列（monotonic deque）以 O(1) 维护窗口的最大值和最小值。

## 卡点

- 首次提交时 cost 的乘法没有提升为 long，溢出导致 WA。WA 后立刻定位并修复。

## 关键点

- 计数正确性的依据：cost 单调，所以 [l, r+1] 合法时 [l, r] 一定合法，每个 right 的最小合法 left 随 right 单调不减。于是以 right 结尾的合法子数组恰好是 [left, right] … [right, right]，共 `right - left + 1` 个。
- 量级估算：`len × (max - min)` 最大约 10^5 × 10^9 = 10^14，超过 int 范围，必须用 long 计算；答案最大约 n²/2 ≈ 5×10^9，也需要 long。

## 复杂度

- 时间 O(n)：每个下标最多进出每个队列各一次。
- 空间 O(n)。

## 注意

- 收缩循环不会把窗口缩空：left == right 时 cost = 0 ≤ k，循环必然停止，`peekLast()` 不会作用于空队列。
- `deque.peekLast() == left` 是 Integer 与 int 比较，会自动拆箱，是安全的；如果两边都是 Integer，要改用 `equals`。
- 同一个 Deque 最好统一方向（`offerLast` 入队，`peekFirst/pollFirst` 出队），避免 `push`（头部）和 `peekLast`（尾部）混用造成方向混乱。
- 看到 k ≤ 10^15、参数类型是 long 时，写乘法前先估算量级。