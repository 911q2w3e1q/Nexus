package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class NukerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting radius = new Setting("半径", 2f, 1f, 6f, 1f);
	public final Setting type = new Setting("类型",
		new String[] {"全部", "矿石", "仅方块"}, 0);
	public final Setting liquids = new Setting("挖液体", false);
	
	public NukerHack()
	{
		super("Nuker", "世界");
		addSetting(radius);
		addSetting(type);
		addSetting(liquids);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		tick++;
		if(tick < 2)
			return;
		tick = 0;
		
		BlockPos center = MC.player.blockPosition();
		for(int dx = -(int)radius.value; dx <= (int)radius.value; dx++)
			for(int dy = -(int)radius.value; dy <= (int)radius.value; dy++)
				for(int dz = -(int)radius.value; dz <= (int)radius.value; dz++)
				{
					BlockPos pos = center.offset(dx, dy, dz);
					BlockState bs = MC.level.getBlockState(pos);
					if(bs.isAir())
						continue;
					if(!liquids.boolValue
						&& !bs.getFluidState().isEmpty())
						continue;
					if(!shouldMine(bs))
						continue;
					MC.gameMode.destroyBlock(pos);
					return;
				}
	}
	
	private boolean shouldMine(BlockState bs)
	{
		String mode = type.getEnumValue();
		if(mode.equals("全部"))
			return true;
		Block b = bs.getBlock();
		if(mode.equals("矿石"))
			return b == Blocks.DIAMOND_ORE
				|| b == Blocks.DEEPSLATE_DIAMOND_ORE
				|| b == Blocks.IRON_ORE
				|| b == Blocks.DEEPSLATE_IRON_ORE
				|| b == Blocks.GOLD_ORE
				|| b == Blocks.DEEPSLATE_GOLD_ORE
				|| b == Blocks.COPPER_ORE
				|| b == Blocks.DEEPSLATE_COPPER_ORE
				|| b == Blocks.COAL_ORE
				|| b == Blocks.DEEPSLATE_COAL_ORE
				|| b == Blocks.LAPIS_ORE
				|| b == Blocks.DEEPSLATE_LAPIS_ORE
				|| b == Blocks.REDSTONE_ORE
				|| b == Blocks.DEEPSLATE_REDSTONE_ORE
				|| b == Blocks.EMERALD_ORE
				|| b == Blocks.DEEPSLATE_EMERALD_ORE
				|| b == Blocks.NETHER_QUARTZ_ORE
				|| b == Blocks.NETHER_GOLD_ORE
				|| b == Blocks.ANCIENT_DEBRIS
				|| b == Blocks.ENDER_CHEST;
		if(mode.equals("仅方块"))
			return b == Blocks.OBSIDIAN
				|| b == Blocks.NETHERITE_BLOCK
				|| b == Blocks.EMERALD_BLOCK
				|| b == Blocks.DIAMOND_BLOCK
				|| b == Blocks.GOLD_BLOCK
				|| b == Blocks.IRON_BLOCK
				|| b == Blocks.CHEST;
		return true;
	}
}
