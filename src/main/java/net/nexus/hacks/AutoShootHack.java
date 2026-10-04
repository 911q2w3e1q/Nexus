package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;

public final class AutoShootHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean pulled;
	
	public AutoShootHack()
	{
		super("AutoShoot", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.BOW))
			return;
		
		Entity target = null;
		double best = 30.0;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			double d = EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		if(target == null)
			return;
		
		EntityUtils.lookAt(target);
		
		if(!pulled)
		{
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			pulled = true;
		}
		else if(MC.player.getTicksUsingItem() >= 20)
		{
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			pulled = false;
		}
	}
}
