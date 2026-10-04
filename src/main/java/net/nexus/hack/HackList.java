package net.nexus.hack;

import java.util.ArrayList;
import java.util.List;

import net.nexus.hacks.*;
import net.nexus.util.Translate;

public final class HackList
{
	private final List<Hack> hacks = new ArrayList<>();
	
	// 移动
	public final FlightHack flight = new FlightHack();
	public final FakeFlightHack fakeFlight = new FakeFlightHack();
	public final SpeedHack speed = new SpeedHack();
	public final LongJumpHack longJump = new LongJumpHack();
	public final AntiVoidHack antiVoid = new AntiVoidHack();
	public final VClipHack vClip = new VClipHack();
	public final FreezeHack freeze = new FreezeHack();
	public final HoleSnapHack holeSnap = new HoleSnapHack();
	public final NoFallHack noFall = new NoFallHack();
	public final JesusHack jesus = new JesusHack();
	
	// 战斗
	public final KillauraHack killaura = new KillauraHack();
	public final CrystalAuraHack crystalAura = new CrystalAuraHack();
	public final AutoClickerHack autoClicker = new AutoClickerHack();
	public final TargetStrafeHack targetStrafe = new TargetStrafeHack();
	public final BurrowHack burrow = new BurrowHack();
	public final AutoPearlHack autoPearl = new AutoPearlHack();
	public final AutoRodHack autoRod = new AutoRodHack();
	public final AutoShootHack autoShoot = new AutoShootHack();
	public final AntiCreeperHack antiCreeper = new AntiCreeperHack();
	public final TriggerBotHack triggerBot = new TriggerBotHack();
	public final CriticalsHack criticals = new CriticalsHack();
	public final AutoArmorHack autoArmor = new AutoArmorHack();
	public final AutoSwordHack autoSword = new AutoSwordHack();
	public final AutoTotemHack autoTotem = new AutoTotemHack();
	public final AutoBlockHack autoBlock = new AutoBlockHack();
	public final MultiAuraHack multiAura = new MultiAuraHack();
	public final BowAimbotHack bowAimbot = new BowAimbotHack();
	public final WTapHack wTap = new WTapHack();
	public final AutoSoupHack autoSoup = new AutoSoupHack();
	public final KillPotionHack killPotion = new KillPotionHack();
	public final AnchorAuraHack anchorAura = new AnchorAuraHack();
	public final PlayerAuraHack playerAura = new PlayerAuraHack();
	public final MobAuraHack mobAura = new MobAuraHack();
	public final BedAuraHack bedAura = new BedAuraHack();
	public final AutoShieldHack autoShield = new AutoShieldHack();
	public final ReachHack reach = new ReachHack();
	public final ProtectHack protect = new ProtectHack();
	
	// 世界
	public final ScaffoldHack scaffold = new ScaffoldHack();
	public final NukerHack nuker = new NukerHack();
	public final AutoToolHack autoTool = new AutoToolHack();
	public final SurroundHack surround = new SurroundHack();
	public final HoleFillHack holeFill = new HoleFillHack();
	public final AutoTrapHack autoTrap = new AutoTrapHack();
	public final WallHackHack wallHack = new WallHackHack();
	public final ExcavatorHack excavator = new ExcavatorHack();
	public final AutoMineHack autoMine = new AutoMineHack();
	public final FastBreakHack fastBreak = new FastBreakHack();
	public final TowerHack tower = new TowerHack();
	public final PlacerHack placer = new PlacerHack();
	public final RemoverHack remover = new RemoverHack();
	public final BlockerHack blocker = new BlockerHack();
	public final TunnellerHack tunneller = new TunnellerHack();
	public final MineAreaHack mineArea = new MineAreaHack();
	public final AirPlaceHack airPlace = new AirPlaceHack();
	public final AutoLadderHack autoLadder = new AutoLadderHack();
	public final BridgeHack bridge = new BridgeHack();
	public final LiquidInteractHack liquidInteract = new LiquidInteractHack();
	public final ChestStealerHack chestStealer = new ChestStealerHack();
	
	// 渲染
	public final FullbrightHack fullbright = new FullbrightHack();
	public final ZoomHack zoom = new ZoomHack();
	public final TracersHack tracers = new TracersHack();
	public final NoFogHack noFog = new NoFogHack();
	public final NameTagsHack nameTags = new NameTagsHack();
	public final PlayerEspHack playerEsp = new PlayerEspHack();
	public final MobEspHack mobEsp = new MobEspHack();
	public final ChestEspHack chestEsp = new ChestEspHack();
	public final ItemEspHack itemEsp = new ItemEspHack();
	public final HealthTagsHack healthTags = new HealthTagsHack();
	public final BreadcrumbsHack breadcrumbs = new BreadcrumbsHack();
	public final XRayHack xRay = new XRayHack();
	
	// 聊天/其他
	public final SpammerHack spammer = new SpammerHack();
	public final AutoGGHack autoGG = new AutoGGHack();
	public final AutoRespawnHack autoRespawn = new AutoRespawnHack();
	public final AutoWalkHack autoWalk = new AutoWalkHack();
	public final AutoSprintHack autoSprint = new AutoSprintHack();
	public final PanicHack panic = new PanicHack();
	public final CleanUpHack cleanUp = new CleanUpHack();
	public final AutoDropHack autoDrop = new AutoDropHack();
	public final NoPumpkinHack noPumpkin = new NoPumpkinHack();
	public final AntiAFKHack antiAFK = new AntiAFKHack();
	public final AutoReplyHack autoReply = new AutoReplyHack();
	public final AutoFishHack autoFish = new AutoFishHack();
	public final AutoEatHack autoEat = new AutoEatHack();
	public final FriendsHack friends = new FriendsHack();
	public final ChatFilterHack chatFilter = new ChatFilterHack();
	public final AutoLeaveHack autoLeave = new AutoLeaveHack();
	public final AutoReconnectHack autoReconnect = new AutoReconnectHack();
	public final AutoSwitchHack autoSwitch = new AutoSwitchHack();
	public final BunnyHopHack bunnyHop = new BunnyHopHack();
	public final AirJumpHack airJump = new AirJumpHack();
	public final HighJumpHack highJump = new HighJumpHack();
	public final GlideHack glide = new GlideHack();
	public final StrafeHack strafe = new StrafeHack();
	public final ParkourHack parkour = new ParkourHack();
	public final SafeWalkHack safeWalk = new SafeWalkHack();
	public final NoSlowDownHack noSlowDown = new NoSlowDownHack();
	public final AutoSwimHack autoSwim = new AutoSwimHack();
	public final StepHack step = new StepHack();
	public final SpiderHack spider = new SpiderHack();
	public final JetpackHack jetpack = new JetpackHack();
	public final NoWebHack noWeb = new NoWebHack();
	public final IceSpeedHack iceSpeed = new IceSpeedHack();
	public final FastLadderHack fastLadder = new FastLadderHack();
	public final DolphinHack dolphin = new DolphinHack();
	public final SneakHack sneak = new SneakHack();
	public final FreecamHack freecam = new FreecamHack();
	
	public HackList()
	{
		// 移动
		hacks.add(flight); hacks.add(fakeFlight); hacks.add(speed);
		hacks.add(longJump); hacks.add(antiVoid); hacks.add(vClip);
		hacks.add(freeze); hacks.add(holeSnap); hacks.add(noFall);
		hacks.add(jesus);
		// 战斗
		hacks.add(killaura); hacks.add(crystalAura); hacks.add(autoClicker);
		hacks.add(targetStrafe); hacks.add(burrow); hacks.add(autoPearl);
		hacks.add(autoRod); hacks.add(autoShoot); hacks.add(antiCreeper);
		hacks.add(triggerBot); hacks.add(criticals); hacks.add(autoArmor);
		hacks.add(autoSword); hacks.add(autoTotem); hacks.add(autoBlock);
		hacks.add(multiAura); hacks.add(bowAimbot);
		hacks.add(wTap); hacks.add(autoSoup); hacks.add(killPotion);
		hacks.add(anchorAura); hacks.add(playerAura); hacks.add(mobAura);
		hacks.add(bedAura); hacks.add(autoShield); hacks.add(reach);
		hacks.add(protect);
		// 世界
		hacks.add(scaffold); hacks.add(nuker); hacks.add(autoTool);
		hacks.add(surround); hacks.add(holeFill); hacks.add(autoTrap);
		hacks.add(wallHack); hacks.add(excavator); hacks.add(autoMine);
		hacks.add(fastBreak); hacks.add(tower); hacks.add(placer);
		hacks.add(remover); hacks.add(blocker);
		hacks.add(tunneller); hacks.add(mineArea); hacks.add(airPlace);
		hacks.add(autoLadder); hacks.add(bridge); hacks.add(liquidInteract);
		hacks.add(chestStealer);
		// 渲染
		hacks.add(fullbright); hacks.add(zoom); hacks.add(tracers);
		hacks.add(noFog); hacks.add(nameTags);
		// 聊天/其他
		hacks.add(spammer); hacks.add(autoGG); hacks.add(autoRespawn);
		hacks.add(autoWalk); hacks.add(autoSprint);
		hacks.add(bunnyHop); hacks.add(airJump); hacks.add(highJump);
		hacks.add(glide); hacks.add(strafe); hacks.add(parkour);
		hacks.add(safeWalk); hacks.add(noSlowDown); hacks.add(autoSwim);
		hacks.add(step); hacks.add(spider); hacks.add(jetpack);
		hacks.add(noWeb); hacks.add(iceSpeed); hacks.add(fastLadder);
		hacks.add(dolphin); hacks.add(sneak); hacks.add(freecam);
		hacks.add(panic); hacks.add(cleanUp); hacks.add(autoDrop);
		hacks.add(noPumpkin); hacks.add(antiAFK); hacks.add(autoReply);
		hacks.add(autoFish); hacks.add(autoEat);
		hacks.add(friends); hacks.add(chatFilter);
		hacks.add(autoLeave); hacks.add(autoReconnect);
		hacks.add(autoSwitch);
		
		registerNames();
	}
	
	private void registerNames()
	{
		// 移动
		Translate.put("name.flight", "飞行");
		Translate.put("name.fakeflight", "假飞");
		Translate.put("name.speed", "加速");
		Translate.put("name.longjump", "远跳");
		Translate.put("name.antivoid", "防虚空");
		Translate.put("name.vclip", "垂直瞬移");
		Translate.put("name.freeze", "冻结");
		Translate.put("name.holesnap", "进洞吸附");
		Translate.put("name.nofall", "防摔落");
		Translate.put("name.jesus", "水上行走");
		// 战斗
		Translate.put("name.killaura", "杀戮光环");
		Translate.put("name.crystalaura", "水晶光环");
		Translate.put("name.autoclicker", "自动连点");
		Translate.put("name.targetstrafe", "环绕目标");
		Translate.put("name.burrow", "埋身");
		Translate.put("name.autopearl", "自动珍珠");
		Translate.put("name.autorod", "钓鱼竿连击");
		Translate.put("name.autoshoot", "自动投掷");
		Translate.put("name.anticreeper", "防苦力怕");
		// 世界
		Translate.put("name.scaffold", "自动搭路");
		Translate.put("name.nuker", "核爆挖矿");
		Translate.put("name.autotool", "自动选工具");
		Translate.put("name.surround", "环绕保护");
		Translate.put("name.holefill", "填洞");
		Translate.put("name.autotrap", "自动封人");
		Translate.put("name.wallhack", "隔墙透视");
		// 渲染
		Translate.put("name.fullbright", "亮度增强");
		Translate.put("name.zoom", "缩放");
		// 聊天/其他
		Translate.put("name.spammer", "刷屏机");
		Translate.put("name.autogg", "自动GG");
		Translate.put("name.autorespawn", "自动重生");
		Translate.put("name.autowalk", "自动行走");
		Translate.put("name.autosprint", "自动疾跑");
		// 移动新增
		Translate.put("name.bunnyhop", "兔子跳");
		Translate.put("name.airjump", "空中跳");
		Translate.put("name.highjump", "高跳");
		Translate.put("name.glide", "滑翔");
		Translate.put("name.strafe", "定向冲刺");
		Translate.put("name.parkour", "自动跳跃");
		Translate.put("name.safewalk", "安全行走");
		Translate.put("name.noslowdown", "防减速");
		Translate.put("name.autoswim", "自动游泳");
		// 战斗新增
		Translate.put("name.triggerbot", "扳机");
		Translate.put("name.criticals", "暴击");
		Translate.put("name.autoarmor", "自动穿甲");
		Translate.put("name.autosword", "自动切剑");
		Translate.put("name.autototem", "自动图腾");
		Translate.put("name.autoblock", "自动格挡");
		Translate.put("name.multiaura", "多重光环");
		Translate.put("name.bowaimbot", "弓箭瞄准");
		// 世界新增
		Translate.put("name.excavator", "挖掘机");
		Translate.put("name.automine", "自动挖矿");
		Translate.put("name.fastbreak", "快速挖掘");
		Translate.put("name.tower", "搭塔");
		Translate.put("name.placer", "自动放置");
		Translate.put("name.remover", "自动移除");
		Translate.put("name.blocker", "阻挡");
		// 渲染新增
		Translate.put("name.tracers", "追踪线");
		Translate.put("name.nofog", "去雾");
		Translate.put("name.nametags", "名字标签");
		Translate.put("name.playeresp", "玩家透视");
		Translate.put("name.mobesp", "怪物透视");
		Translate.put("name.chestesp", "箱子透视");
		Translate.put("name.itemesp", "掉落物透视");
		// 其他新增
		Translate.put("name.panic", "一键关闭");
		Translate.put("name.cleanup", "清理掉落物");
		Translate.put("name.autodrop", "自动丢弃");
		Translate.put("name.nopumpkin", "防南瓜遮挡");
		Translate.put("name.antiafk", "防挂机");
		Translate.put("name.autoreply", "自动回复");
		Translate.put("name.autofish", "自动钓鱼");
		Translate.put("name.autoeat", "自动吃东西");
		// v3 新增
		Translate.put("name.step", "自动爬台阶");
		Translate.put("name.spider", "蜘蛛爬墙");
		Translate.put("name.jetpack", "喷气背包");
		Translate.put("name.wtap", "甩刀");
		Translate.put("name.autosoup", "自动喝汤");
		Translate.put("name.killpotion", "杀戮药水");
		Translate.put("name.anchoraura", "锚点光环");
		Translate.put("name.friends", "好友列表");
		Translate.put("name.chatfilter", "聊天过滤");
		Translate.put("name.autoleave", "自动退出");
		Translate.put("name.autoreconnect", "自动重连");
		// v4 新增
		Translate.put("name.noweb", "防蜘蛛网");
		Translate.put("name.icespeed", "冰上加速");
		Translate.put("name.fastladder", "快速爬梯");
		Translate.put("name.dolphin", "海豚游泳");
		Translate.put("name.sneak", "自动潜行");
		Translate.put("name.freecam", "灵魂出窍");
		Translate.put("name.playeraura", "玩家光环");
		Translate.put("name.mobaura", "怪物光环");
		Translate.put("name.bedaura", "炸床光环");
		Translate.put("name.autoshield", "自动盾牌");
		Translate.put("name.reach", "延伸");
		Translate.put("name.protect", "保护");
		Translate.put("name.tunneller", "隧道挖掘");
		Translate.put("name.minearea", "区域挖掘");
		Translate.put("name.airplace", "空中放置");
		Translate.put("name.autoladder", "自动爬梯子");
		Translate.put("name.bridge", "搭桥");
		Translate.put("name.liquidinteract", "液体交互");
		Translate.put("name.cheststealer", "箱子窃取");
		Translate.put("name.healthtags", "血条标签");
		Translate.put("name.breadcrumbs", "足迹线");
		Translate.put("name.xray", "幻透");
		Translate.put("name.autoswitch", "自动换物品");
	}
	
	public void onTick()
	{
		for(Hack hack : hacks)
			if(hack.isEnabled() && hack.checkTrigger())
				hack.onTick();
	}
	
	public List<Hack> getAll()
	{
		return hacks;
	}
}
