package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

public final class XRayHack extends Hack
{
	private static boolean active;
	private static final Set<Block> WHITELIST = new HashSet<>();
	
	private final Minecraft MC = Minecraft.getInstance();
	
	public XRayHack()
	{
		super("XRay", "渲染");
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	/** 幻透白名单：矿石/箱子/刷怪笼等 */
	public static boolean shouldShow(BlockState state)
	{
		Block b = state.getBlock();
		if(b == Blocks.AIR)
			return true;
		if(b == Blocks.OBSIDIAN || b == Blocks.CRYING_OBSIDIAN
			|| b == Blocks.NETHER_PORTAL)
			return true;
		return WHITELIST.contains(b);
	}
	
	static
	{
		// 矿石
		WHITELIST.add(Blocks.COAL_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_COAL_ORE);
		WHITELIST.add(Blocks.IRON_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_IRON_ORE);
		WHITELIST.add(Blocks.COPPER_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_COPPER_ORE);
		WHITELIST.add(Blocks.GOLD_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_GOLD_ORE);
		WHITELIST.add(Blocks.REDSTONE_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_REDSTONE_ORE);
		WHITELIST.add(Blocks.LAPIS_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_LAPIS_ORE);
		WHITELIST.add(Blocks.DIAMOND_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_DIAMOND_ORE);
		WHITELIST.add(Blocks.EMERALD_ORE);
		WHITELIST.add(Blocks.DEEPSLATE_EMERALD_ORE);
		WHITELIST.add(Blocks.NETHER_GOLD_ORE);
		WHITELIST.add(Blocks.NETHER_QUARTZ_ORE);
		WHITELIST.add(Blocks.ANCIENT_DEBRIS);
		// 特殊方块
		WHITELIST.add(Blocks.CHEST);
		WHITELIST.add(Blocks.ENDER_CHEST);
		WHITELIST.add(Blocks.TRAPPED_CHEST);
		WHITELIST.add(Blocks.SPAWNER);
		WHITELIST.add(Blocks.BEDROCK);
		WHITELIST.add(Blocks.WATER);
		WHITELIST.add(Blocks.LAVA);
		WHITELIST.add(Blocks.TNT);
		WHITELIST.add(Blocks.BEACON);
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
	}
}
