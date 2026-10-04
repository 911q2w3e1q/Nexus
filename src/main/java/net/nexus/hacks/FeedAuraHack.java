package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;

public final class FeedAuraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting range = new Setting("范围", 4f, 2f, 8f, 0.5f);
	
	public FeedAuraHack()
	{
		super("FeedAura", "世界");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		tick++;
		if(tick < 20)
			return;
		tick = 0;
		
		// 喂最近的可繁殖动物
		Entity target = null;
		double best = range.value;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(!(e instanceof Animal a) || !a.isAlive())
				continue;
			if(a.isBaby())
				continue;
			double d = EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		if(target != null)
		{
			EntityUtils.lookAt(target);
			MC.gameMode.interact(MC.player, target,
				InteractionHand.MAIN_HAND);
		}
	}
}
