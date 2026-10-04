package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;

public final class RemoverHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public RemoverHack()
	{
		super("Remover", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(MC.hitResult instanceof BlockHitResult hit)
			MC.gameMode.destroyBlock(hit.getBlockPos());
	}
}
