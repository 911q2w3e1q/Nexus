package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class TargetStrafeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean right;
	
	public final Setting radius = new Setting("半径", 2.5f, 1f, 6f, 0.5f);
	public final Setting speed = new Setting("速度", 1f, 0.5f, 3f, 0.1f);
	
	public TargetStrafeHack()
	{
		super("TargetStrafe", "战斗");
		addSetting(radius);
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double best = 8.0;
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
		
		// 绕目标转圈：切线方向移动
		Vec3 t = EntityUtils.getCenter(target);
		Vec3 p = MC.player.position();
		double dx = p.x - t.x;
		double dz = p.z - t.z;
		double dist = Math.sqrt(dx * dx + dz * dz);
		if(dist < 0.1)
			return;
		
		right = !right;
		double tx = -dz / dist * (right ? 1 : -1);
		double tz = dx / dist * (right ? 1 : -1);
		MC.player.setDeltaMovement(tx * speed.value,
			MC.player.getDeltaMovement().y, tz * speed.value);
		
		// 面朝目标
		EntityUtils.lookAt(target);
	}
}
