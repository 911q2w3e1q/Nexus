package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;

public final class AutoBlockHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoBlockHack()
	{
		super("AutoBlock", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 有目标时举盾格挡
		Entity target = null;
		double best = 4.5;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			double d = EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		
		boolean hasShield = MC.player.getOffhandItem()
			.is(Items.SHIELD)
			|| MC.player.getMainHandItem().is(Items.SHIELD);
		if(!hasShield)
			return;
		
		if(target != null)
		{
			MC.player.setShiftKeyDown(false);
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
		}
		else
		{
			MC.player.stopUsingItem();
		}
	}
}
