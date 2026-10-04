# Known Issues for versions ≤ a0.6

🔴 Critical: Test code shipped in release

`NexusClient.runTestHook()` — automatically pops ClickGUI every 700 ticks, then closes it again at 500 ticks. `testGuiMode` and `testGuiTicks` are sitting right there in the main class.

This is debug code, not a feature. You forgot to strip it before shipping the a0.6 release — users get force-toggled in and out of the menu every 35 seconds in-game. This isn't a bug, it's an incident.

🟠 Flight implementation: Reskinned creative flight

- Directly modifies `abilities.flying` — server-side anticheat will flag this as creative flight instantly
- No mode switching (creative / glide / jet) — just one hardcoded fly mode
- 0.05, 0.9 are magic numbers with zero comments
- Vertical movement only has ascend/descend, no hover

This code would be considered sloppy even in a tutorial. Wurst's Flight has 5 modes, anticheat bypass, and speed curves — this doesn't even have a fraction of Meteor's implementation quality.

🟡 Architecture issues: Works, but unprofessional

1. Every one of the 107 Hacks stores its own `MC` reference — redundant, should all go through `NexusClient.getInstance().MC`
2. Killaura speed control uses `tick++` compared against a float→int cast — speed=1.1 and speed=1.9 behave exactly the same, precision is wasted
3. Hack base class forces every hack to have `sneakTrigger` / `sprintTrigger` — two public final fields shoved in regardless of whether they're used, lazy design
4. Setting class has both `value` and `boolValue` — one class handles all types via field switching, no generics, no subclasses
5. Magic numbers everywhere — 0.05, 0.9, 700, 200, 500, zero constants defined

## Summary

It runs, and that's it. Stacking 107 features for count points is fine, but every feature's implementation depth is "barely functional" level. Core features like Flight are written like a freshman assignment, and shipping release with test code still in it means you have no basic release workflow.
