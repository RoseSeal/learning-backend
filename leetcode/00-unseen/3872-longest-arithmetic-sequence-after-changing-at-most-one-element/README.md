# 3872. Longest Arithmetic Sequence After Changing At Most One Element

记录：H1 · 超时 · 首交未过（迁移自旧 unseen 记录）

## 题意

整数数组 nums，至多把一个元素替换为任意整数，然后选一个等差子数组（相邻差为常数），求最长长度。

## 我的思考

思路很简单，但实现麻烦，老是写错。

第一版（双状态维护，WA）：

```java
class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;

        int len1 = 2;
        int diff1 = nums[1] - nums[0];
        
        int len2 = 2;
        int diff2 = diff1;
        int idx = -1;

        int ans = 2;
        for (int i = 2; i < n; i++) {
            int currDiff = nums[i] - nums[i - 1];
            boolean update1 = false;
            boolean update2 = false;

            if (currDiff == diff1) len1++;
            else update1 = true;

            if (i - 1 == idx) currDiff = nums[i] - nums[i - 2] - diff2;
            else if (i == idx) currDiff = diff2;

            if (currDiff == diff2) len2++;
            else update2 = true;

            if (update1) {
                if (len1 + 1 >= len2) {
                    idx = i;
                    len2 = len1 + 1;
                    diff2 = diff1;
                }
                diff1 = currDiff;
                len1 = 2;
            } else if (update2) {
                ans = Math.max(ans, len2);
                len2 = len1 + 1;
                diff2 = diff1;
            }
        }

        ans = Math.max(ans, len2);
        return ans;
    }
}
```

在 case `nums = [13,4,11,10,1]` 错了。

本题消耗时间太长，思路很直接，但实现非常繁琐，最终超时。

## 卡点

- 用两组（长度，公差）状态同时维护"未替换"和"已替换"，实现繁琐，在 [13,4,11,10,1] 上出错。

## 关键点

## 主流解对照

## 复杂度

（补）O(n)，空间 O(1)。

## 注意
