package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;

public final class AutoRodHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int rodTick;
	
	public final Setting range = new Setting("范围", 6f, 2f, 12f, 0.5f);
	
	public AutoRodHack()
	{
		super("AutoRod", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double best = range.value;
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
		
		// 甩竿击退
		rodTick++;
		if(rodTick > 10)
		{
			rodTick = 0;
			EntityUtils.lookAt(target);
			if(MC.player.getMainHandItem().is(Items.FISHING_ROD)
				|| hasRod())
			{
				MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			}
		}
	}
	
	private boolean hasRod()
	{
		for(int i = 0; i < 9; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && s.is(Items.FISHING_ROD))
			{
				MC.player.getInventory().setSelectedSlot(i);
				return true;
			}
		}
		return false;
	}
}
