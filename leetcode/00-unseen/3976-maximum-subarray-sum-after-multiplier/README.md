# 3976. Maximum Subarray Sum After Multiplier

记录：H2 · 超时 · 首交未过（迁移自旧 unseen 记录）

## 题意

整数数组 nums 与正整数 k。选一个子数组，把其中每个数乘以 k 或除以 k（正数向下取整，负数向上取整，即向 0 取整），之后求结果数组的最大非空子数组和。操作的子数组和求和的子数组可以不同。

## 我的思考

第一版（贪心，WA）：

```java
class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long maxSum1 = Integer.MIN_VALUE;
        long currSum1 = 0;
        for (int num : nums) {
            currSum1 = Math.max(currSum1 + num, num);
            maxSum1 = Math.max(maxSum1, currSum1);
        }
        if (maxSum1 < 0) return maxSum1 / k;
        maxSum1 = maxSum1 * k;

        long currSum2 = 0;
        long maxSum2 = 0;
        int idx = 0;
        int n = nums.length;
        while (idx < n) {
            while (idx < n && nums[idx] < 0) idx++;
            while (idx < n && currSum2 + nums[idx] > 0) {
                currSum2 += nums[idx];
                idx++;
            }
            int part2 = 0;
            while (idx < n) {
                int num = nums[idx];
                idx++;
                if (num < 0) {
                    currSum2 = Math.max(currSum2 + part2 / k, part2);
                    part2 = 0;
                    currSum2 += num / k;
                    if (currSum2 <= 0) {
                        currSum2 = 0;
                        break;
                    }
                } else {
                    part2 += num;
                    maxSum2 = Math.max(maxSum2, currSum2 + part2);
                } 
            }
        }

        return Math.max(maxSum1, maxSum2);
    }
}
```

没通过，找不出 bug。反例：

[2,-1,-1,2,-1,-1,2], k=2
             ↓
[2, 0, 0,1, 0, 0,2] = 5

这个思路已经写了 80min，一直在修修补补。

第二版（贪心修补，仍 WA）：

```java
class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long maxSum1 = Integer.MIN_VALUE;
        long currSum1 = 0;
        for (int num : nums) {
            currSum1 = Math.max(currSum1 + num, num);
            maxSum1 = Math.max(maxSum1, currSum1);
        }
        if (maxSum1 < 0) return maxSum1 / k;
        maxSum1 = maxSum1 * k;

        long currSum2 = 0;
        long maxSum2 = 0;
        int idx = 0;
        int n = nums.length;
        while (idx < n) {
            while (idx < n && nums[idx] < 0) idx++;
            while (idx < n && nums[idx] > 0) {
                currSum2 += nums[idx];
                idx++;
            }
            int part2 = 0;
            while (idx < n) {
                int num = nums[idx];
                idx++;
                if (num < 0) {
                    currSum2 = Math.max(currSum2 + part2 / k, part2);
                    part2 = 0;
                    currSum2 += num / k;
                    if (currSum2 <= 0) {
                        currSum2 = 0;
                        break;
                    }
                } else {
                    part2 += num;
                    maxSum2 = Math.max(maxSum2, currSum2 + part2);
                } 
            }
        }

        return Math.max(maxSum1, maxSum2);
    }
}
```

还是在另一个 case WA。

晚上重新写：不执著于贪心就很简单。

最终结果：
早上耗时80min，没写出来。
晚上耗时13min，AC了。

## 卡点

- 贪心方案早上写了 80min，反复修补仍然 WA。

## 关键点

- 不执著于贪心，改为三状态 DP 后很简单。

## 主流解对照

## 复杂度

（补）时间 O(n)，空间 O(1)。

## 注意
