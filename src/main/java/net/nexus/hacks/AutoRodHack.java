package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

public final class AutoRodHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoRodHack()
	{
		super("AutoRod", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.FISHING_ROD))
			return;
		
		if(MC.hitResult instanceof EntityHitResult hit)
		{
			MC.gameMode.attack(MC.player, hit.getEntity());
			MC.player.swing(InteractionHand.MAIN_HAND);
		}
	}
}
