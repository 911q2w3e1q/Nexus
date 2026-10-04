# version ≤ a0.6 已知问题

🔴 致命问题：测试代码打包进 release

`NexusClient.runTestHook()` — 每 700 tick 自动弹 ClickGUI，到 500 tick 又自动关掉。`testGuiMode`、`testGuiTicks` 两个字段明晃晃写在主类里。

这是调试代码，不是功能。发 a0.6 release 的时候没删，用户进游戏每隔 35 秒就被强制弹一次菜单再关掉，这不是 bug，这是事故。

🟠 Flight 实现：创造模式飞行换皮

- 直接改 `abilities.flying`，服务器反作弊看一眼就知道你开了创造飞行
- 没有任何模式切换（创造/滑翔/喷气），就一种硬飞
- 0.05、0.9 全是魔法数字，零注释
- 垂直方向只有上升/下降，没有悬停

这代码放教程里都嫌糙。Wurst 的 Flight 有 5 种模式、反作弊规避、速度曲线，这个连 Meteor 的零头都不到。

🟡 架构问题：能跑但不专业

1. 107 个 Hack 每个都存一份 `MC` 引用 — 冗余，应该统一走 `NexusClient.getInstance().MC`
2. Killaura 速度控制用 `tick++` 跟 float 转 int 比较 — speed=1.1 和 speed=1.9 效果完全一样，精度喂狗了
3. Hack 基类强制每个 hack 都带 `sneakTrigger` / `sprintTrigger` — 不管用不用都塞两个 public final 字段，设计懒惰
4. Setting 类同时有 `value` 和 `boolValue` — 一个类干所有类型的活，靠字段区分，不用泛型不用子类
5. 魔法数字满天飞 — 0.05、0.9、700、200、500，一个常量都没定义

## 总结

能跑，仅此而已。107 个功能堆量可以，但每个功能的实现深度都是"刚能工作"的水平。Flight 这种核心功能写得像大一作业，测试代码不删就发版说明没有基本的发布流程。
