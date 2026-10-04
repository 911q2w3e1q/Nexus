package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public final class WTapHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tapTick;
	
	public final Setting tapTicks = new Setting("松开时长", 2f, 1f, 5f, 1f);
	
	public WTapHack()
	{
		super("WTap", "战斗");
		addSetting(tapTicks);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 攻击到实体后松开前进键模拟 W 点击（击退增强）
		Entity target = null;
		double best = 4.0;
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
		{
			if(tapTick > 0)
			{
				MC.options.keyUp.setDown(true);
				tapTick = 0;
			}
			return;
		}
		
		if(MC.player.distanceTo(target) < 3.0)
		{
			if(tapTick == 0)
				MC.options.keyUp.setDown(false);
			tapTick++;
			if(tapTick >= (int)tapTicks.value)
			{
				MC.options.keyUp.setDown(true);
				tapTick = 0;
			}
		}
	}
}
