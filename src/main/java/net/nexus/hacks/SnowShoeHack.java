package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;

public final class SnowShoeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public SnowShoeHack()
	{
		super("SnowShoe", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 雪地不陷：站在雪/粉雪上正常行走（简化：粉雪上不减速）
		var b = MC.level.getBlockState(
			MC.player.blockPosition().below()).getBlock();
		if(b == Blocks.POWDER_SNOW
			|| b == Blocks.SNOW_BLOCK
			|| b == Blocks.SNOW)
		{
			MC.player.setDeltaMovement(
				MC.player.getDeltaMovement().x,
				Math.max(MC.player.getDeltaMovement().y, -0.5),
				MC.player.getDeltaMovement().z);
		}
	}
}
