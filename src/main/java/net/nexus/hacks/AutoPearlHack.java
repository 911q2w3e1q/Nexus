package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

public final class AutoPearlHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoPearlHack()
	{
		super("AutoPearl", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.ENDER_PEARL))
			return;
		
		if(MC.hitResult instanceof EntityHitResult)
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
