package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class WallHackHack extends Hack
{
	private static boolean active;
	
	public WallHackHack()
	{
		super("WallHack", "世界");
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	/** 全透墙：只保留基岩/边界/液体/边界方块可见 */
	public static boolean shouldShow(BlockState state)
	{
		if(state.isAir())
			return true;
		var b = state.getBlock();
		return b == Blocks.BEDROCK
			|| b == Blocks.WATER || b == Blocks.LAVA
			|| b == Blocks.BARRIER
			|| b == Blocks.VOID_AIR;
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
