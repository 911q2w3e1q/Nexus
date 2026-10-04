package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

public final class AutoShootHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoShootHack()
	{
		super("AutoShoot", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		var item = MC.player.getMainHandItem().getItem();
		if(item != Items.EGG && item != Items.SNOWBALL)
			return;
		
		if(MC.hitResult instanceof EntityHitResult)
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
