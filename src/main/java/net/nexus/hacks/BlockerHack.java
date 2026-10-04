package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

public final class BlockerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public BlockerHack()
	{
		super("Blocker", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(MC.hitResult instanceof BlockHitResult hit)
		{
			BlockHitResult place = new BlockHitResult(
				hit.getLocation(), hit.getDirection(),
				hit.getBlockPos().relative(hit.getDirection()), false);
			MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND,
				place);
		}
	}
}
