package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class TargetStrafeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean clockwise = true;
	
	public TargetStrafeHack()
	{
		super("TargetStrafe", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double bestDist = 6;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			double d = MC.player.distanceTo(e);
			if(d < bestDist)
			{
				bestDist = d;
				target = e;
			}
		}
		
		if(target == null)
			return;
		
		Vec3 tp = target.position();
		Vec3 pp = MC.player.position();
		double angle = Math.atan2(pp.z - tp.z, pp.x - tp.x);
		angle += clockwise ? Math.PI / 2 : -Math.PI / 2;
		
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(-Math.sin(angle) * 0.5, v.y,
			Math.cos(angle) * 0.5);
		
		if(Math.random() < 0.005)
			clockwise = !clockwise;
	}
}
