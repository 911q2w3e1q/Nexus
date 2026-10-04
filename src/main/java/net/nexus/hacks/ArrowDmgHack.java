package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public final class ArrowDmgHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 4f, 2f, 8f, 0.5f);
	
	public ArrowDmgHack()
	{
		super("ArrowDmg", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 近战攻击附带强力击退（模拟弓箭击退效果）
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
		if(target instanceof LivingEntity le)
		{
			EntityUtils.lookAt(le);
			MC.gameMode.attack(MC.player, le);
			MC.player.swing(InteractionHand.MAIN_HAND);
			// 附加击退
			var dir = MC.player.position()
				.subtract(le.position()).normalize();
			le.knockback(1.5, dir.x, dir.z);
		}
	}
}
