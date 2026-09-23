# LeetCode Roadmap

本文件只承担两件事：题目清单与状态（Console Coding 模块的唯一事实源），以及 R19 重做队列。执行规则见 `EXECUTION.md`；算法模块的现状判断、训练结构与验收见 `~/Documents/面试/算法模块执行规划.md`。

- 陌生题：在 Console Daily 顶部当天添加，落入 Round 0，之后与主线题流程完全一致。
- 主线新题：Round 13 起按文档顺序推进（文档顺序即推进顺序）。
- 重做：从 Console Review tab 取队首；`[r]` / `[x/r]` 在提交时自动登记到 R19。

### 标记说明
- `[x]` 通过：独立、未超时、首交通过
- `[x/r]` 值得重做：H2、超时或首交未过
- `[r]` 需要重做：H1（关键一步没想到）
- `[-]` 会员题跳过
- `[ ]` 未做

Console 按记录栏给出建议标记，提交前可改。
（2026-09-20 重排：旧“执行计划 / 四 Phase / Execution Notes / UNSEEN 执行日志”已由 EXECUTION.md 与算法模块执行规划取代，原文见 git 历史。）

---

# ============== Round 0 · 陌生题 [Phase: UNSEEN] ==============

当天在 Console 添加（题号 + 名称 + 难度），题面与约束写进题目笔记。目录 `00-unseen/`。
以下 26 道为 2026-09-20 从旧 `unseen/` 场次迁移，标记按记录初标、待本人确认（见算法模块执行规划“未决问题”）。

- [x] 4025. Minimum Penalty [Easy]
- [x] 4026. Maximum Gap Between Stations [Medium]
- [x] 4022. K-th Digit in Infinite String [Medium]
- [x] 3994. Minimum Adjacent Swaps [Medium]
- [x/r] 3998. Transform Binary String Using Subsequence Sort [Medium]
- [x/r] 3923. Minimum Generations to Target Point [Medium]
- [x] 3965. Finish Time of Tasks I [Medium]
- [r] 3933. Largest Local Values in a Matrix II [Medium]
- [x] 4012. Count of Unfinished Tasks After Each Shift [Medium]
- [x/r] 4008. Minimum Initial Strength to Defeat All Monsters [Medium]
- [x] 3970. Shortest Path With At Most K Consecutive Identical Characters [Medium]
- [x] 3980. Minimum Operations to Transform Binary String [Medium]
- [x/r] 3976. Maximum Subarray Sum After Multiplier [Medium]
- [r] 3910. Count Connected Subgraphs with Even Node Sum [Hard]
- [x/r] 3905. Multi Source Flood Fill [Medium]
- [x/r] 3858. Minimum Bitwise OR From Grid [Medium]
- [x] 3868. Minimum Cost to Equalize Arrays Using Swaps [Medium]
- [x] 3863. Minimum Operations to Sort a String [Medium]
- [r] 3872. Longest Arithmetic Sequence After Changing At Most One Element [Medium]
- [x/r] 3882. Minimum XOR Path in a Grid [Medium]
- [r] 3877. Minimum Removals to Achieve Target XOR [Medium]
- [x] 3886. Sum of Sortable Integers [Hard]
- [x] 3891. Minimum Increase to Maximize Special Indices [Medium]
- [x] 3900. Longest Balanced Substring After One Swap [Medium]
- [x] 3919. Minimum Cost to Move Between Indices [Medium]
- [x] 3854. Minimum Operations to Make Array Parity Alternating [Medium]
- [x] 3849. maximum-bitwise-xor-after-rearrangement [Medium]
- [x] 3844. Longest Almost-Palindromic Substring [Medium]
- [x/r] 3835. Count Subarrays With Cost Less Than or Equal to K [Medium]
- [x] 3840. house-robber-v [Medium]
- [x] 3829. design-ride-sharing-system [Medium]
- [r] 3825. longest-strictly-increasing-subsequence-with-non-zero-bitwise-and [Medium]

# ============== Round 1 · Union-Find [Phase: NEW] ==============

- [x] 547. Number of Provinces [Medium]
- [x/r] 990. Satisfiability of Equality Equations [Medium]
- [x] 1971. Find if Path Exists in Graph [Easy]
- [x/r] 685. Redundant Connection II [Hard]
- [x] 399. Evaluate Division [Medium]
- [r] 721. Accounts Merge [Medium]
- [x] 1319. Number of Operations to Make Network Connected [Medium]
- [x] 1202. Smallest String With Swaps [Medium]
- [x/r] 959. Regions Cut By Slashes [Medium]
- [-] 305. Number of Islands II [Hard]
- [x] 827. Making A Large Island [Hard]
- [-] 323. Number of Connected Components in an Undirected Graph [Medium]
- [x] 839. Similar String Groups [Hard]
- [-] 1135. Connecting Cities With Minimum Cost [Medium]
- [x] 2685. Count the Number of Complete Components [Medium]
- [r] 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree [Hard]

# ============== Round 2 · Design 核心 [Phase: NEW] ==============

字节 / 阿里 / Meta 一面 No.1 命中区。LRU 要做到 5min 内手撸 doubly-linked + HashMap。

- [x/r] 146. LRU Cache [Medium]
- [r] 460. LFU Cache [Hard]
- [x/r] 380. Insert Delete GetRandom O(1) [Medium]
- [x] 705. Design HashSet [Easy]
- [x] 706. Design HashMap [Easy]
- [x] 173. Binary Search Tree Iterator [Medium]
- [x] 622. Design Circular Queue [Medium]

# ============== Round 3 · Advanced Array / Matrix [Phase: NEW] ==============

国内大厂一面命中率最高。

- [x/r] 41. First Missing Positive [Hard]
- [x] 48. Rotate Image [Medium]
- [r] 54. Spiral Matrix [Medium]
- [x] 57. Insert Interval [Medium]
- [r] 73. Set Matrix Zeroes [Medium]
- [x] 80. Remove Duplicates from Sorted Array II [Medium]
- [x] 86. Partition List [Medium]
- [x/r] 179. Largest Number [Medium]
- [r] 220. Contains Duplicate III [Hard]
- [x] 289. Game of Life [Medium]
- [r] 324. Wiggle Sort II [Medium]
- [x] 912. Sort an Array [Medium]
- [x] 973. K Closest Points to Origin [Medium]

# ============== Round 4 · 旧轮反虚假进步审计（R1-R12）[Phase: AUDIT] ==============

题目已在旧 Round 标 `[x]`，本轮作为独立审计任务用 `[ ]` 起步：
- 简单题限时 25min，中等 35min，Hard 50min
- 出声讲：题意复述 / 朴素解 / 不变量 / 复杂度 / 反例
- 不查 Java API，不依赖 LC 测试用例 debug
- 审计通过标 `[x]`；卡时标 `[x/r]`；崩盘标 `[r]`
- **某专题若失败率 > 30%，该组其余未列题目补审计**
- 旧 R1-R6（二分/前缀和/堆/滑窗/双指针）完成最早、衰减最重，抽审 7 道兜底

旧 R1-R6 抽审（衰减最重的早期专题）：

- [x] 33. Search in Rotated Sorted Array [Medium]（R1，字节高频）
- [x] 153. Find Minimum in Rotated Sorted Array [Medium]（R1）
- [x] 162. Find Peak Element [Medium]（R1）
- [x] 560. Subarray Sum Equals K [Medium]（R2，字节高频）
- [x] 347. Top K Frequent Elements [Medium]（R3）
- [r] 424. Longest Repeating Character Replacement [Medium]（R5）
- [x/r] 287. Find the Duplicate Number [Medium]（R1，二分 + 快慢指针双解都要讲）

旧 R7-R12 审计：

- [x] 297. Serialize and Deserialize Binary Tree [Hard]（R9）
- [x] 236. Lowest Common Ancestor of a Binary Tree [Medium]（R10）
- [x] 235. Lowest Common Ancestor of a BST [Medium]（R10）
- [x] 437. Path Sum III [Medium]（R10）
- [x] 124. Binary Tree Maximum Path Sum [Hard]（R10，已有笔记，限时验证）
- [x] 199. Binary Tree Right Side View [Medium]（R9，原未刷）
- [x] 103. Binary Tree Zigzag Level Order Traversal [Medium]（R9，原未刷）
- [x] 25. Reverse Nodes in k-Group [Hard]（R7，已有笔记，限时验证）
- [x/r] 138. Copy List with Random Pointer [Medium]（R7）
- [x] 142. Linked List Cycle II [Medium]（R7）
- [x] 92. Reverse Linked List II [Medium]（R7，原未刷，顺带补）
- [x] 46. Permutations [Medium]（R11）
- [x] 47. Permutations II [Medium]（R11，原未刷）
- [x] 79. Word Search [Medium]（R11）
- [x] 56. Merge Intervals [Medium]（R12）
- [x] 230. Kth Smallest Element in a BST [Medium]（R10，原未刷，字节高频）

# ============== Round 5 · Monotonic Stack 变体 [Phase: NEW] ==============

已过模板题（42/84/239），本轮补变体辨识能力。

- [r] 321. Create Maximum Number [Hard]
- [x/r] 1856. Maximum Subarray Min-Product [Medium]
- [r] 2104. Sum of Subarray Ranges [Medium]
- [x/r] 1944. Number of Visible People in a Queue [Hard]
- [r] 862. Shortest Subarray with Sum at Least K [Hard]
- [x] 901. Online Stock Span [Medium]
- [x/r] 456. 132 Pattern [Medium]
- [x] 735. Asteroid Collision [Medium]
- [x] 1673. Find the Most Competitive Subsequence [Medium]
- [x] 2398. Maximum Number of Robots Within Budget [Hard]

# ============== Round 6 · Math / Bit / Trick 补盲 [Phase: NEW] ==============

补 421 暴露过的位运算盲区 + 字节字符串模拟高频。

- [x] 50. Pow(x, n) [Medium]
- [x] 29. Divide Two Integers [Medium]
- [x] 43. Multiply Strings [Medium]
- [x] 172. Factorial Trailing Zeroes [Medium]
- [x/r] 166. Fraction to Recurring Decimal [Medium]
- [x] 415. Add Strings [Easy]
- [x] 273. Integer to English Words [Hard]
- [x] 8. String to Integer (atoi) [Medium]（字节字符串模拟高频，边界陷阱密集）
- [x] 165. Compare Version Numbers [Medium]（字节高频）

# ============== Round 7 · Design 第二批 [Phase: NEW] ==============

- [x] 895. Maximum Frequency Stack [Hard]
- [x] 341. Flatten Nested List Iterator [Medium]
- [x] 1381. Design a Stack With Increment Operation [Medium]
- [x] 641. Design Circular Deque [Medium]
- [-] 362. Design Hit Counter [Medium]
- [-] 359. Logger Rate Limiter [Easy]
- [x] 284. Peeking Iterator [Medium]
- [x/r] 381. Insert Delete GetRandom O(1) - Duplicates allowed [Hard]

# ============== Round 8 · DP cherry-pick [Phase: NEW] ==============

DP 是强项（三轮沉淀），仅 cherry-pick 高级变体作为知识扩展。

- [x] 221. Maximal Square [Medium]（旧 R16 唯一未做）
- [x] 97. Interleaving String [Medium]
- [x/r] 10. Regular Expression Matching [Hard]
- [x] 44. Wildcard Matching [Hard]
- [x] 91. Decode Ways [Medium]
- [x] 329. Longest Increasing Path in a Matrix [Hard]
- [x] 403. Frog Jump [Hard]

# ============== Round 13 · Segment Tree / BIT [Phase: NEW] ==============

中后期面试分水岭题，公理推导型有优势。

- [ ] 307. Range Sum Query - Mutable [Medium]
- [ ] 308. Range Sum Query 2D - Mutable [Hard]
- [ ] 315. Count of Smaller Numbers After Self [Hard]
- [ ] 327. Count of Range Sum [Hard]
- [ ] 493. Reverse Pairs [Hard]
- [ ] 218. The Skyline Problem [Hard]

# ============== Round 15 · Sweep Line [Phase: NEW] ==============

区间问题进阶，中后台 / 排程类系统设计的算法底座。

- [ ] 1235. Maximum Profit in Job Scheduling [Hard]
- [ ] 729. My Calendar I [Medium]
- [ ] 731. My Calendar II [Medium]
- [ ] 732. My Calendar III [Hard]

# ============== Round 14 · String 算法 [Phase: NEW] ==============

KMP / Manacher / Rolling Hash，顺带训 Java String API 肌肉记忆。

- [ ] 28. Find the Index of the First Occurrence in a String [Easy]
- [ ] 214. Shortest Palindrome [Hard]
- [ ] 459. Repeated Substring Pattern [Easy]
- [ ] 686. Repeated String Match [Medium]
- [ ] 1392. Longest Happy Prefix [Hard]

# ============== Round 16 · 博弈 DP [Phase: NEW] ==============

DP 的反向思考分支，Meta / 字节有时出。

- [ ] 877. Stone Game [Medium]
- [ ] 486. Predict the Winner [Medium]
- [ ] 464. Can I Win [Medium]
- [ ] 1140. Stone Game II [Medium]

# ============== Round 17 · Reservoir Sampling + 概率 + Trie 深度变体 [Phase: NEW] ==============

AI 应用 / 推荐系统 / 搜索建议的算法基础。

- [ ] 382. Linked List Random Node [Medium]
- [ ] 398. Random Pick Index [Medium]
- [ ] 470. Implement Rand10() Using Rand7() [Medium]（字节/腾讯概率题常客，拒绝采样）
- [ ] 472. Concatenated Words [Hard]
- [ ] 642. Design Search Autocomplete System [Hard]
- [ ] 1268. Search Suggestions System [Medium]

# ============== Round 18 · Hard 终局 mock [Phase: MOCK] ==============

**贴着投递面试期（2027-02 ~ 04）推进**，每周 2 道完整 mock，与 wrong-list 维护、UNSEEN 场次并行。

- [ ] 4. Median of Two Sorted Arrays [Hard]
- [ ] 23. Merge k Sorted Lists [Hard]
- [ ] 25. Reverse Nodes in k-Group [Hard]
- [ ] 42. Trapping Rain Water [Hard]
- [ ] 72. Edit Distance [Medium]
- [ ] 76. Minimum Window Substring [Hard]
- [ ] 84. Largest Rectangle in Histogram [Hard]
- [ ] 124. Binary Tree Maximum Path Sum [Hard]
- [ ] 146. LRU Cache [Medium]
- [ ] 212. Word Search II [Hard]
- [ ] 239. Sliding Window Maximum [Hard]
- [ ] 410. Split Array Largest Sum [Hard]

# ============== Round 12 · 多线程 / 并发同步 [Phase: NEW] ==============

Java 后端八股的实际落地。配合 JUC 知识体系一起复习。

> 2026-09-20：排到主线最后。Java 并发写法对以 Go 为主的后端技术面价值有限，但题量小，保留按需做。

- [ ] 1114. Print in Order [Easy]
- [ ] 1115. Print FooBar Alternately [Medium]
- [ ] 1116. Print Zero Even Odd [Medium]
- [ ] 1117. Building H2O [Medium]

# ============== Round 19 · Review & Re-attempt [Phase: AUDIT/REVIEW] ==============

所有 [r] / [x/r] 题目的重做队列（Console Review tab 的数据源）。Daily 提交为 [r] / [x/r] 时自动登记到下方“新轮”分组；节奏见文末“Review 调度建议”。
（2026-07-18 中期复盘补录新 R1-R6 标记题，见 `insight/midterm-review-2026-07.md` 第六节；2026-08-21 补录新 R7-R8 标记题 381 / 10，见 `insight/round-07-design.md`、`insight/round-08-dp-cherry-pick.md` 执行层。）

## 新轮 [r]

- [ ] 460. LFU Cache [r]（新 R2，Hard，R7 Design 第二批的直接前置） ｜重做未过：2026-07-19
- [ ] 321. Create Maximum Number [r]（新 R5，Hard，重做标准见 round-05）
- [ ] 2104. Sum of Subarray Ranges [r]（新 R5，重做标准：不试符号、先写相等归属论证）
- [ ] 862. Shortest Subarray with Sum at Least K [r]（新 R5，Hard，重做标准：主流单调队列 O(n) 版 + 讲清与支配关系推导的等价性）
- [ ] 424. Longest Repeating Character Replacement [r]（新 R4，重做标准：历史 maxFreq 不回退版 + 讲清为何不污染答案）
- [ ] 54. Spiral Matrix [r]（新 R3，字节高频）
- [ ] 73. Set Matrix Zeroes [r]（新 R3）
- [ ] 220. Contains Duplicate III [r]（新 R3，Hard）
- [ ] 324. Wiggle Sort II [r]（新 R3，重点：三向 partition + quickselect 下标版）
- [ ] 721. Accounts Merge [r]（新 R1）
- [ ] 1489. Find Critical and Pseudo-Critical Edges in MST [r]（新 R1，Hard，重做标准：先写主流"禁用/强制 Kruskal"版）
- [ ] 3933. Largest Local Values in a Matrix II [r]（陌生题，Medium）
- [ ] 3910. Count Connected Subgraphs with Even Node Sum [r]（陌生题，Hard）
- [ ] 3872. Longest Arithmetic Sequence After Changing At Most One Element [r]（陌生题，Medium）
- [ ] 3877. Minimum Removals to Achieve Target XOR [r]（陌生题，Medium）
- [ ] 3825. longest-strictly-increasing-subsequence-with-non-zero-bitwise-and [r]（陌生题，Medium）

## 新轮 [x/r]

> 注意：380（新 R2）与 381（新 R7）是不同题目，勿混。

- [ ] 990. Satisfiability of Equality Equations [x/r]（新 R1）
- [ ] 685. Redundant Connection II [x/r]（新 R1，Hard）
- [ ] 959. Regions Cut By Slashes [x/r]（新 R1）
- [ ] 380. Insert Delete GetRandom O(1) [x/r]（新 R2）
- [ ] 41. First Missing Positive [x/r]（新 R3，Hard）
- [ ] 179. Largest Number [x/r]（新 R3）
- [ ] 287. Find the Duplicate Number [x/r]（新 R4，重做标准：白板完整推出 a = kb - c 并讲出声）
- [ ] 138. Copy List with Random Pointer [x/r]（新 R4）
- [ ] 1856. Maximum Subarray Min-Product [x/r]（新 R5）
- [ ] 1944. Number of Visible People in a Queue [x/r]（新 R5，Hard）
- [ ] 456. 132 Pattern [x/r]（新 R5）
- [ ] 166. Fraction to Recurring Decimal [x/r]（新 R6，重做标准：直接用 `/` 与 `%` 只模拟小数部分，讲清"余数重复即循环"）
- [ ] 381. Insert Delete GetRandom O(1) - Duplicates allowed [x/r]（新 R7，Hard，2026-08-21 补录，重做标准：直接写"末尾即待删值则免交换"的最终版，出声讲清概率不变量与 Set 别名成立条件）
- [ ] 10. Regular Expression Matching [x/r]（新 R8，Hard，2026-08-21 补录，重做标准：先写主流二维 DP，再讲清一维压缩中 `up` / `pre` / `dp[j-1]` 的语义与 `*` 不更新 `pre` 的理由）
- 146. LRU Cache [x/r]（新 R2）→ 不单列，由 R18 自然重考
- [ ] 3998. Transform Binary String Using Subsequence Sort [x/r]（陌生题，Medium）
- [ ] 3923. Minimum Generations to Target Point [x/r]（陌生题，Medium）
- [ ] 4008. Minimum Initial Strength to Defeat All Monsters [x/r]（陌生题，Medium）
- [ ] 3976. Maximum Subarray Sum After Multiplier [x/r]（陌生题，Medium）
- [ ] 3905. Multi Source Flood Fill [x/r]（陌生题，Medium）
- [ ] 3858. Minimum Bitwise OR From Grid [x/r]（陌生题，Medium）
- [ ] 3882. Minimum XOR Path in a Grid [x/r]（陌生题，Medium）
- [ ] 3835. Count Subarrays With Cost Less Than or Equal to K [x/r]（陌生题，Medium）

## 旧轮存量

- [ ] 224. Basic Calculator [x/r]（旧 R8）
- [ ] 227. Basic Calculator II [x/r]（旧 R8）
- [ ] 316. Remove Duplicate Letters [r]（旧 R8）
- [ ] 417. Pacific Atlantic Water Flow [x/r]（旧 R13）
- [ ] 442. Find All Duplicates in an Array [x/r]（旧 R6）
- [ ] 542. 01 Matrix [x/r]（旧 R13）
- [ ] 752. Open the Lock [r]（旧 R13）
- [ ] 909. Snakes and Ladders [x/r]（旧 R13）
- [ ] 1203. Sort Items by Groups Respecting Dependencies [r]（旧 R14）
- [ ] 1249. Minimum Remove to Make Valid Parentheses [x/r]（旧 R8）
- [ ] 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance [x/r]（旧 R14）
- [ ] 127. Word Ladder [x/r]（旧 R13）
- [ ] 310. Minimum Height Trees [x/r]（旧 R14）

---

# ============== 已完成索引区（不再推进） ==============

## 旧 Round 1 · Binary Search
- [x] 704. Binary Search [Easy]
- [x] 35. Search Insert Position [Easy]
- [x] 34. Find First and Last Position of Element in Sorted Array [Medium]
- [x] 69. Sqrt(x) [Easy]
- [x] 33. Search in Rotated Sorted Array [Medium]
- [x] 81. Search in Rotated Sorted Array II [Medium]
- [x] 153. Find Minimum in Rotated Sorted Array [Medium]
- [x] 162. Find Peak Element [Medium]
- [x] 287. Find the Duplicate Number [Medium]
- [x] 74. Search a 2D Matrix [Medium]
- [x] 240. Search a 2D Matrix II [Medium]
- [x] 875. Koko Eating Bananas [Medium]
- [x] 1011. Capacity To Ship Packages Within D Days [Medium]
- [x] 410. Split Array Largest Sum [Hard]
- [x] 540. Single Element in a Sorted Array [Medium]

## 旧 Round 2 · Prefix Sum & Difference Array
- [x] 303. Range Sum Query - Immutable [Easy]
- [x] 304. Range Sum Query 2D - Immutable [Medium]
- [x] 1109. Corporate Flight Bookings [Medium]
- [-] 370. Range Addition [Medium]
- [x] 560. Subarray Sum Equals K [Medium]
- [x] 523. Continuous Subarray Sum [Medium]
- [x] 525. Contiguous Array [Medium]
- [x] 974. Subarray Sums Divisible by K [Medium]
- [x] 1074. Number of Submatrices That Sum to Target [Hard]
- [x] 238. Product of Array Except Self [Medium]
- [x] 724. Find Pivot Index [Easy]
- [x] 1248. Count Number of Nice Subarrays [Medium]
- [x] 930. Binary Subarrays With Sum [Medium]
- [x] 1371. Find the Longest Substring Containing Vowels in Even Counts [Medium]
- [x] 1094. Car Pooling [Medium]

## 旧 Round 3 · Heap / Priority Queue
- [x] 215. Kth Largest Element in an Array [Medium]
- [x] 703. Kth Largest Element in a Stream [Easy]
- [x] 1046. Last Stone Weight [Easy]
- [x] 347. Top K Frequent Elements [Medium]
- [x] 23. Merge k Sorted Lists [Hard]
- [x] 295. Find Median from Data Stream [Hard]
- [x] 373. Find K Pairs with Smallest Sums [Medium]
- [x] 378. Kth Smallest Element in a Sorted Matrix [Medium]
- [x] 692. Top K Frequent Words [Medium]
- [-] 253. Meeting Rooms II [Medium]
- [x] 621. Task Scheduler [Medium]
- [x] 767. Reorganize String [Medium]
- [x] 355. Design Twitter [Medium]
- [x] 480. Sliding Window Median [Hard]
- [x] 2402. Meeting Rooms III [Hard]

## 旧 Round 4 · Trie & Bit Manipulation
- [x] 208. Implement Trie (Prefix Tree) [Medium]
- [x] 136. Single Number [Easy]
- [x] 191. Number of 1 Bits [Easy]
- [x] 231. Power of Two [Easy]
- [x] 211. Design Add and Search Words Data Structure [Medium]
- [x] 648. Replace Words [Medium]
- [x] 677. Map Sum Pairs [Medium]
- [x] 137. Single Number II [Medium]
- [x] 260. Single Number III [Medium]
- [x] 212. Word Search II [Hard]
- [x] 421. Maximum XOR of Two Numbers in an Array [Medium]
- [x] 318. Maximum Product of Word Lengths [Medium]
- [x] 338. Counting Bits [Easy]
- [x] 1707. Maximum XOR With an Element From Array [Hard]
- [x] 461. Hamming Distance [Easy]

## 旧 Round 5 · Sliding Window Core
- [x] 3. Longest Substring Without Repeating Characters [Medium]
- [x] 209. Minimum Size Subarray Sum [Medium]
- [x] 904. Fruit Into Baskets [Medium]
- [x] 438. Find All Anagrams in a String [Medium]
- [x] 76. Minimum Window Substring [Hard]
- [x] 567. Permutation in String [Medium]
- [x] 1004. Max Consecutive Ones III [Medium]
- [x] 424. Longest Repeating Character Replacement [Medium]
- [x] 1208. Get Equal Substrings Within Budget [Medium]
- [-] 159. Longest Substring with At Most Two Distinct Characters [Medium]
- [-] 340. Longest Substring with At Most K Distinct Characters [Medium]
- [x] 992. Subarrays with K Different Integers [Hard]
- [x] 1456. Maximum Number of Vowels in a Substring of Given Length [Medium]
- [x] 713. Subarray Product Less Than K [Medium]
- [x] 643. Maximum Average Subarray I [Easy]

## 旧 Round 6 · Array / Hash / Two Pointers
- [x] 1. Two Sum [Easy]
- [x] 15. 3Sum [Medium]
- [x] 18. 4Sum [Medium]
- [x] 49. Group Anagrams [Medium]
- [x] 128. Longest Consecutive Sequence [Medium]
- [x] 217. Contains Duplicate [Easy]
- [x] 242. Valid Anagram [Easy]
- [x] 454. 4Sum II [Medium]
- [x] 349. Intersection of Two Arrays [Easy]
- [x] 26. Remove Duplicates from Sorted Array [Easy]
- [x] 27. Remove Element [Easy]
- [x] 88. Merge Sorted Array [Easy]
- [x] 189. Rotate Array [Medium]
- [x] 283. Move Zeroes [Easy]
- [x] 977. Squares of a Sorted Array [Easy]
- [x] 16. 3Sum Closest [Medium]
- [x] 167. Two Sum II - Input Array Is Sorted [Medium]
- [x] 268. Missing Number [Easy]
- [x] 36. Valid Sudoku [Medium]
- [x] 274. H-Index [Medium]
- [x] 334. Increasing Triplet Subsequence [Medium]
- [x] 443. String Compression [Medium]
- [x] 581. Shortest Unsorted Continuous Subarray [Medium]
- [x/r] 442. Find All Duplicates in an Array [Medium]

## 旧 Round 7 · Linked List
- [x] 21. Merge Two Sorted Lists [Easy]
- [x] 206. Reverse Linked List [Easy]
- [x] 203. Remove Linked List Elements [Easy]
- [x] 24. Swap Nodes in Pairs [Medium]
- [x] 19. Remove Nth Node From End of List [Medium]
- [x] 160. Intersection of Two Linked Lists [Easy]
- [x] 141. Linked List Cycle [Easy]
- [x] 142. Linked List Cycle II [Medium]
- [x] 143. Reorder List [Medium]
- [x] 25. Reverse Nodes in k-Group [Hard]
- [x] 138. Copy List with Random Pointer [Medium]
- [x] 2. Add Two Numbers [Medium]
- [x] 445. Add Two Numbers II [Medium]
- [x] 148. Sort List [Medium]

## 旧 Round 8 · Stack / Queue / Parsing
- [x] 20. Valid Parentheses [Easy]
- [x] 155. Min Stack [Medium]
- [x] 232. Implement Queue using Stacks [Easy]
- [x] 225. Implement Stack using Queues [Easy]
- [x] 150. Evaluate Reverse Polish Notation [Medium]
- [x] 71. Simplify Path [Medium]
- [x] 394. Decode String [Medium]
- [x/r] 227. Basic Calculator II [Medium]
- [x/r] 224. Basic Calculator [Hard]
- [x] 402. Remove K Digits [Medium]
- [x] 1047. Remove All Adjacent Duplicates In String [Easy]
- [x] 496. Next Greater Element I [Easy]
- [x] 503. Next Greater Element II [Medium]
- [r] 316. Remove Duplicate Letters [Medium]
- [x/r] 1249. Minimum Remove to Make Valid Parentheses [Medium]

## 旧 Round 9 · Binary Tree Traversal & Construction
- [x] 94. Binary Tree Inorder Traversal [Easy]
- [x] 144. Binary Tree Preorder Traversal [Easy]
- [x] 145. Binary Tree Postorder Traversal [Easy]
- [x] 102. Binary Tree Level Order Traversal [Medium]
- [x] 104. Maximum Depth of Binary Tree [Easy]
- [x] 226. Invert Binary Tree [Easy]
- [x] 101. Symmetric Tree [Easy]
- [x] 105. Construct Binary Tree from Preorder and Inorder Traversal [Medium]
- [x] 106. Construct Binary Tree from Inorder and Postorder Traversal [Medium]
- [x] 297. Serialize and Deserialize Binary Tree [Hard]
- [x] 222. Count Complete Tree Nodes [Easy]
- [x] 543. Diameter of Binary Tree [Easy]

## 旧 Round 10 · BST / Tree DP / LCA
- [x] 98. Validate Binary Search Tree [Medium]
- [x] 700. Search in a Binary Search Tree [Easy]
- [x] 235. Lowest Common Ancestor of a Binary Search Tree [Medium]
- [x] 236. Lowest Common Ancestor of a Binary Tree [Medium]
- [x] 110. Balanced Binary Tree [Easy]
- [x] 112. Path Sum [Easy]
- [x] 113. Path Sum II [Medium]
- [x] 124. Binary Tree Maximum Path Sum [Hard]
- [x] 437. Path Sum III [Medium]
- [x] 337. House Robber III [Medium]
- [x] 572. Subtree of Another Tree [Easy]
- [x] 654. Maximum Binary Tree [Medium]

## 旧 Round 11 · Backtracking
- [x] 77. Combinations [Medium]
- [x] 78. Subsets [Medium]
- [x] 90. Subsets II [Medium]
- [x] 39. Combination Sum [Medium]
- [x] 40. Combination Sum II [Medium]
- [x] 216. Combination Sum III [Medium]
- [x] 46. Permutations [Medium]
- [x] 17. Letter Combinations of a Phone Number [Medium]
- [x] 22. Generate Parentheses [Medium]
- [x] 79. Word Search [Medium]
- [x] 131. Palindrome Partitioning [Medium]
- [x] 93. Restore IP Addresses [Medium]
- [x] 51. N-Queens [Hard]
- [x] 37. Sudoku Solver [Hard]

## 旧 Round 12 · Greedy / Interval
- [x] 55. Jump Game [Medium]
- [x] 45. Jump Game II [Medium]
- [x] 134. Gas Station [Medium]
- [x] 135. Candy [Hard]
- [x] 406. Queue Reconstruction by Height [Medium]
- [x] 435. Non-overlapping Intervals [Medium]
- [x] 452. Minimum Number of Arrows to Burst Balloons [Medium]
- [x] 455. Assign Cookies [Easy]
- [x] 763. Partition Labels [Medium]
- [x] 738. Monotone Increasing Digits [Medium]
- [x] 860. Lemonade Change [Easy]
- [x] 1005. Maximize Sum Of Array After K Negations [Easy]
- [x] 376. Wiggle Subsequence [Medium]
- [x] 56. Merge Intervals [Medium]

## 旧 Round 13 · Graph BFS / DFS
- [x] 200. Number of Islands [Medium]
- [x] 695. Max Area of Island [Medium]
- [x] 733. Flood Fill [Easy]
- [x/r] 542. 01 Matrix [Medium]
- [x] 994. Rotting Oranges [Medium]
- [x] 1162. As Far from Land as Possible [Medium]
- [x/r] 417. Pacific Atlantic Water Flow [Medium]
- [x] 130. Surrounded Regions [Medium]
- [x] 529. Minesweeper [Medium]
- [x/r] 127. Word Ladder [Hard]
- [r] 752. Open the Lock [Medium]
- [x] 1091. Shortest Path in Binary Matrix [Medium]
- [x/r] 909. Snakes and Ladders [Medium]
- [x] 934. Shortest Bridge [Medium]
- [x] 1293. Shortest Path in a Grid with Obstacles Elimination [Hard]
- [-] 286. Walls and Gates [Medium]
- [x] 1926. Nearest Exit from Entrance in Maze [Medium]

## 旧 Round 14 · Topological Sort / Shortest Path / MST
- [x] 207. Course Schedule [Medium]
- [x] 210. Course Schedule II [Medium]
- [x] 743. Network Delay Time [Medium]
- [x] 797. All Paths From Source to Target [Medium]
- [-] 269. Alien Dictionary [Hard]
- [x] 2115. Find All Possible Recipes from Given Supplies [Medium]
- [r] 1203. Sort Items by Groups Respecting Dependencies [Hard]
- [x/r] 310. Minimum Height Trees [Medium]
- [x] 802. Find Eventual Safe States [Medium]
- [x] 787. Cheapest Flights Within K Stops [Medium]
- [x] 1368. Minimum Cost to Make at Least One Valid Path in a Grid [Hard]
- [x] 1631. Path With Minimum Effort [Medium]
- [x/r] 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance [Medium]
- [x] 1514. Path with Maximum Probability [Medium]
- [x] 778. Swim in Rising Water [Hard]
- [r] 815. Bus Routes [Hard]
- [x] 1584. Min Cost to Connect All Points [Medium]

## 旧 Round 16 · DP - Linear（[ ] 221 迁移至新 Round 8）
- [x] 53. Maximum Subarray [Medium]
- [x] 70. Climbing Stairs [Easy]
- [x] 62. Unique Paths [Medium]
- [x] 63. Unique Paths II [Medium]
- [x] 64. Minimum Path Sum [Medium]
- [x] 198. House Robber [Medium]
- [x] 213. House Robber II [Medium]
- [x] 746. Min Cost Climbing Stairs [Easy]
- [x] 509. Fibonacci Number [Easy]
- [x] 152. Maximum Product Subarray [Medium]
- [x] 300. Longest Increasing Subsequence [Medium]
- [x] 674. Longest Continuous Increasing Subsequence [Easy]
- [x] 718. Maximum Length of Repeated Subarray [Medium]
- [x] 1035. Uncrossed Lines [Medium]

## 旧 Round 17 · DP - Knapsack / Partition / Stock
- [x] 121. Best Time to Buy and Sell Stock [Easy]
- [x] 122. Best Time to Buy and Sell Stock II [Medium]
- [x] 123. Best Time to Buy and Sell Stock III [Hard]
- [x] 309. Best Time to Buy and Sell Stock with Cooldown [Medium]
- [x] 714. Best Time to Buy and Sell Stock with Transaction Fee [Medium]
- [x] 188. Best Time to Buy and Sell Stock IV [Hard]
- [x] 416. Partition Equal Subset Sum [Medium]
- [x] 494. Target Sum [Medium]
- [x] 322. Coin Change [Medium]
- [x] 518. Coin Change II [Medium]
- [x] 279. Perfect Squares [Medium]
- [x] 1049. Last Stone Weight II [Medium]
- [x] 474. Ones and Zeroes [Medium]
- [x] 377. Combination Sum IV [Medium]
- [x] 343. Integer Break [Medium]

## 旧 Round 18 · DP - String（部分 [ ] 迁移至新 Round 8，剩余 712/1092/1312 优先级低）
- [x] 1143. Longest Common Subsequence [Medium]
- [x] 72. Edit Distance [Medium]
- [x] 583. Delete Operation for Two Strings [Medium]
- [x] 392. Is Subsequence [Easy]
- [x] 115. Distinct Subsequences [Hard]
- [x] 940. Distinct Subsequences II [Hard]
- [x] 516. Longest Palindromic Subsequence [Medium]
- [x] 647. Palindromic Substrings [Medium]
- [x] 5. Longest Palindromic Substring [Medium]

## 旧 Round 19 · DP - Memoization / Advanced（部分 [ ] 迁移至新 Round 8，其余优先级低）
- [x] 96. Unique Binary Search Trees [Medium]
- [x] 95. Unique Binary Search Trees II [Medium]
- [x] 139. Word Break [Medium]

## 旧 Round 21 · Design（[ ] 部分迁移至新 Round 2 + 7）
- [x] 707. Design Linked List [Medium]

---

## Review 调度建议（[r] / [x/r] 题）

（2026-09-20 修订。旧版“按新题数比例穿插”“R9 前 [r] 清零”均已取消，原文见 git 历史。）

- 节奏：每周固定重做位置（见 EXECUTION.md），从 Console Review tab 取队首；不按新题数量触发。
- 每条都有失败记录作证据：重做检验的是“当初那个具体失败是否已修复”，括注中的重做标准即检验点。
- **`[r]` 优先序**：460 → 3910 → 2104 → 3933 → 862 → 3877 → 424 → 3872 → 321 → 54 / 73 / 220 / 324 → 316 / 752 / 1203 → 721 / 1489
- `[x/r]` 按文档序排在全部 `[r]` 之后。
- 结论：通过 → `[x]`；重做（7 天冷却后回原位）；可重做（沉到队尾直到通过）。
- 重做一律执行“出声讲不变量”标准。

# ============== 存档区（2026-09-20 停止推送，不参与解析） ==============

R9–R11 是按“高频”重做已掌握的熟题，没有失败记录作依据，停止推送。需要时可按原清单手动做。

## Round 9 · ByteDance 高频 I [Phase: MOCK]

题目大多在主线已 `[x]`，本轮 mock 模式独立重做（白板限时 45min）。

- [ ] 15. 3Sum [Medium]
- [ ] 31. Next Permutation [Medium]
- [ ] 56. Merge Intervals [Medium]
- [ ] 75. Sort Colors [Medium]
- [ ] 76. Minimum Window Substring [Hard]
- [ ] 146. LRU Cache [Medium]
- [ ] 215. Kth Largest Element in an Array [Medium]
- [ ] 236. Lowest Common Ancestor of a Binary Tree [Medium]
- [ ] 239. Sliding Window Maximum [Hard]
- [ ] 297. Serialize and Deserialize Binary Tree [Hard]
- [ ] 300. Longest Increasing Subsequence [Medium]
- [ ] 322. Coin Change [Medium]

## Round 10 · ByteDance 高频 II 剩余 [Phase: MOCK]

- [ ] 208. Implement Trie (Prefix Tree) [Medium]
- [ ] 253. Meeting Rooms II [Medium]
- [ ] 295. Find Median from Data Stream [Hard]
- [ ] 329. Longest Increasing Path in a Matrix [Hard]
- [ ] 410. Split Array Largest Sum [Hard]

## Round 11 · Tencent 高频 [Phase: MOCK]

已压缩：删去 1/20/70/121 四道边际价值过低的 Easy（多轮覆盖 + 不可能挂），省出时间给 UNSEEN 场次。

- [ ] 3. Longest Substring Without Repeating Characters [Medium]
- [ ] 11. Container With Most Water [Medium]
- [ ] 53. Maximum Subarray [Medium]
- [ ] 55. Jump Game [Medium]
- [ ] 128. Longest Consecutive Sequence [Medium]
- [ ] 206. Reverse Linked List [Easy]（5min 极限标准）
- [ ] 215. Kth Largest Element in an Array [Medium]
- [ ] 300. Longest Increasing Subsequence [Medium]
