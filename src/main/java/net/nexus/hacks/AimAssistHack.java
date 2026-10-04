package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public final class AimAssistHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 6f, 2f, 12f, 0.5f);
	public final Setting angle = new Setting("角度", 30f, 5f, 90f, 5f);
	
	public AimAssistHack()
	{
		super("AimAssist", "战斗");
		addSetting(range);
		addSetting(angle);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 选准星附近（角度内）最近的实体，自动吸附瞄准
		Entity target = null;
		double best = range.value;
		float bestAngleDiff = angle.value;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			double d = EntityUtils.getDistanceTo(e);
			if(d > range.value)
				continue;
			float diff = angleDiff(e);
			if(diff < bestAngleDiff)
			{
				bestAngleDiff = diff;
				best = d;
				target = e;
			}
		}
		
		if(target != null)
			EntityUtils.lookAt(target);
	}
	
	private float angleDiff(Entity e)
	{
		var p = MC.player;
		var t = EntityUtils.getCenter(e);
		double dx = t.x - p.getX();
		double dy = t.y - p.getEyeY();
		double dz = t.z - p.getZ();
		double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
		float pitch = (float)(-Math.toDegrees(Math.atan2(dy,
			Math.sqrt(dx * dx + dz * dz))));
		float yaw = (float)(Math.toDegrees(Math.atan2(-dx, dz)));
		float dyaw = Math.abs(wrap(p.getYRot() - yaw));
		float dpitch = Math.abs(wrap(p.getXRot() - pitch));
		return Math.max(dyaw, dpitch);
	}
	
	private float wrap(float a)
	{
		a %= 360;
		if(a > 180)
			a -= 360;
		if(a < -180)
			a += 360;
		return a;
	}
}
