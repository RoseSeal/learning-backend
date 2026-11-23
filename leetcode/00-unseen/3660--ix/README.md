# 3660. 跳跃游戏 IX

记录：独立 · 未超时 · 首交未过

## 我的思考

题意：从下标 i 可以向右跳到更小的值（j > i 且 nums[j] < nums[i]），也可以向左跳到更大的值（j < i 且 nums[j] > nums[i]），跳跃次数不限。求每个 i 能到达的最大值。

约束：1 ≤ n ≤ 1e5，1 ≤ nums[i] ≤ 1e9 → 允许 O(n log n)。

1. 第一想法：答案就是前缀最大值。
   反例：`10 5 12 7`。对于 5，前缀最大值是 10，但答案是 12：10 可以作为跳板跳到 7，再从 7 向左跳到 12。
2. 修正为「前缀最大值 → 其后缀最小值 → 再取前缀最大值」。
   反例 1：`3 1 4 2 5 3`。对于 1，这样算出 4，正确答案是 5。
   反例 2：`10 11 5 100 6`。对于 10，后缀最小值 5 不是最佳跳板，最远的那个更小值 6 才是。
3. 最终方案：设 p 为 [0, i] 中最大值的下标，k 为 p 右侧最远的、值小于 nums[p] 的下标（不存在时 k = p）。i 从后往前处理：
   - k > i：i 和 k 互相可达，ans[i] = ans[k]（k > i，已经算出）。
   - k == i：右侧没有比 nums[p] 更小的元素，左段无法越过 i，ans[i] = nums[p]。
   k 用后缀最小值加二分求，O(n log n)。
4. 评审后简化为 O(n)：「k > i」等价于 preMax[i] > sufMin[i+1]。条件成立时 ans[i] = ans[i+1]，否则 ans[i] = preMax[i]。

## 卡点

- 把「后缀最小值」当成最佳跳板。实际应该取最远的那个更小值，因为它能连到更右边的元素。反例 `10 11 5 100 6` 是判题发现的，导致首次提交未通过。

## 关键点

- 判断「i 与 k 互相可达」时，以前缀最大值 p 作为中转：路径为 i → p → k 和 k → p → i。
- k == i 时，[0, i] 中所有值都 ≤ nums[p]，i 右侧所有值都 ≥ nums[p]，左段无法向右越过 i。

## 复杂度

- 初版：时间 O(n log n)，空间 O(n)。
- 简化版：时间 O(n)，空间 O(n)。

## 注意

- 初版的分类讨论漏了一个承重步骤：为什么 k < i 不可能。原因是 p 取 [0, i] 中最后一个最大值，(p, i] 中的值都严格小于 nums[p]；所以 p < i 时 i 本身满足条件，k ≥ i。若 k < i，代码会读到还没计算的 ans[k]。
- 简化版中「i 与 i+1 互相可达」的证明：设 j > i 且 nums[j] < nums[p]。若 nums[i+1] < nums[p]，p 可以直接跳到 i+1；否则 nums[i+1] ≥ nums[p] > nums[j]，i+1 可以直接跳到 j（i+1 = j 时它本身就在连通块中）。两种情况下 i+1 都与 p 连通。
- 简化版的 min 要先判断再更新，判断时它表示的是 sufMin[i+1]。

代码：
```java
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] preMax = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            preMax[i] = max;
        }

        int min = nums[n - 1];
        int[] ans = new int[n];
        ans[n - 1] = preMax[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (preMax[i] > min) ans[i] = ans[i + 1];
            else ans[i] = preMax[i];
            min = Math.min(min, nums[i]);
        }

        return ans;
    }
}
```