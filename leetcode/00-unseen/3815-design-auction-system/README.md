# 3815. Design Auction System

记录：独立 · 超时 · 首交通过

## 我的思考

题意：设计一个竞拍系统。

- addBid(userId, itemId, bidAmount)：添加出价，已有则替换。
- updateBid(userId, itemId, newAmount)：替换已有出价（保证已存在）。
- removeBid(userId, itemId)：删除出价（保证已存在）。
- getHighestBidder(itemId)：返回对 itemId 出价最高的 userId；金额相同时返回最大的 userId；没有出价返回 -1。

约束：1 <= userId, itemId <= 5 * 10^4；1 <= bidAmount, newAmount <= 10^9；总调用次数最多 5 * 10^4。

思路：

- 每个 item 维护一个优先队列（PriorityQueue），元素为 [amount, userId]，按金额降序，金额相同时按 userId 降序。
- 用 Map 维护每个 (userId, itemId) 的当前出价。
- 懒删除（lazy deletion）维护最大值：更新只 push 新条目，删除只改 Map；查询时堆顶与 Map 中的当前值不符就 poll，直到堆顶有效。
- 堆中堆积的无效条目会影响插入效率，但总操作数只有 5 * 10^4，可以接受。

## 卡点

- 对 PriorityQueue 等数据结构的 API 不熟悉，编码耗时。
- 一开始把出价表和堆各自抽成子类（UserState / ItemState），写到 getHighestBidder 时发现两者耦合（堆的有效性要靠出价表判定），又合并回单类。

## 关键点

- 堆 + Map + 懒删除：堆负责排序，Map 是有效性的唯一事实源。
- 组合键：`((long) userId << 16) | itemId`，因为 itemId <= 5 * 10^4 < 2^16，不会冲突。

## 复杂度

- 时间：add / update / remove 为 O(log Q)；getHighestBidder 均摊 O(log Q)，每个条目最多被 poll 一次。总计 O(Q log Q)。
- 空间：O(Q)。

## 注意

- 懒删除成立的不变量：对每条当前有效的出价 (u, item, v)，heaps[item] 中至少有一条 (v, u)。每次写入 Map 都同时 push，所以弹掉不符的条目是安全的；旧条目因出价回到同一个值而"复活"时，它也准确描述了当前出价，返回它没有问题。面试要主动讲出这一点。
- 值得自测的边界：update 回到旧值（5 → 7 → 5）；remove 后再 add 同一个值；item 没有任何出价时返回 -1。
- 懒删除天生让堆和出价表耦合，强行拆成两个类，只能给堆类传一个 validator 回调，在一道题的规模下是过度设计。先写扁平版，AC 后再考虑结构。
- updateBid 依赖"update 前一定调用过 add，所以 heaps[itemId] 非空"，这由题目保证，但属于隐式前置条件。把判空放进 updateBid 可以去掉对调用顺序的依赖。
- 首交 AC，用时 50min，超时。