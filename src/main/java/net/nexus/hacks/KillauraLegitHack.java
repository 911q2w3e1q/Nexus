package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public final class KillauraLegitHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private float attackTimer;
	
	public final Setting range = new Setting("范围", 4f, 2f, 6f, 0.5f);
	public final Setting speed = new Setting("攻击间隔", 8f, 1f, 20f, 1f);
	
	public KillauraLegitHack()
	{
		super("KillauraLegit", "战斗");
		addSetting(range);
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 合法版：不自动旋转，只自动攻击范围内的实体
		attackTimer += speed.value;
		if(attackTimer < 20f)
			return;
		attackTimer = 0;
		
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
		if(target != null)
		{
			MC.gameMode.attack(MC.player, target);
			MC.player.swing(InteractionHand.MAIN_HAND);
		}
	}
}
