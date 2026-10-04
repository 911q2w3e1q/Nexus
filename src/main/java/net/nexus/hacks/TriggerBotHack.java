package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public final class TriggerBotHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 6f, 2f, 10f, 0.5f);
	
	public TriggerBotHack()
	{
		super("TriggerBot", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 选准星方向最近的可攻击实体
		Entity target = null;
		double best = range.value;
		for(Entity e : net.nexus.util.EntityUtils.getAttackableEntities(
			true, true))
		{
			double d = net.nexus.util.EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		
		if(target != null)
		{
			net.nexus.util.EntityUtils.lookAt(target);
			MC.gameMode.attack(MC.player, target);
			MC.player.swing(InteractionHand.MAIN_HAND);
		}
	}
}
