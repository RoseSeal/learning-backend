# 4008. Minimum Initial Strength to Defeat All Monsters

记录：独立 · 未标超时 · 首交未过（迁移自旧 unseen 记录）

## 题意

从左到右依次打怪。boosts[i] = [l, r, v] 让下标在 [l, r] 的怪物战斗时临时加成 +v（可叠加）。当前强度 + 加成 ≥ monsters[i] 才能击败，之后强度减去 monsters[i]，低于 0 则置 0。求击败所有怪物所需的最小非负初始强度。约束：n、boosts 数量 ≤ 5e4，值 ≤ 1e9。

## 我的思考

耗时15min
初版漏掉了 if (idx >= 0) ans += monsters[idx--] - currBuff;
lc判错后立刻发现了。

## 卡点

- 初版漏掉 `if (idx >= 0) ans += monsters[idx--] - currBuff;`，LC 判错后立刻发现。

## 关键点

- 对于每个怪物，题目给出了当：buff+currStrength >= monster时，currStrength最低扣到0，这看似可以节省strength，但是遇到一个buff < monster 时，必须用currStrength来补齐，也就是要求currStrength大于0，但一但currStrength大于0，前面就不可能发生利用buff > monster进而规避currStrength消耗的情况（strength只降不升），因此，利用buff > monster来节省消耗，只能发生在末尾端，并且在第一次遇到buff < monster结束。

## 主流解对照

## 复杂度

复杂度O(m + n) + O(n)

## 注意
