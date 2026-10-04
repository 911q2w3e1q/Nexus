package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class StrafeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public StrafeHack()
	{
		super("Strafe", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				Vec3 v = MC.player.getDeltaMovement();
		double yaw = Math.toRadians(MC.player.getYRot());
		double speed = Math.sqrt(v.x * v.x + v.z * v.z);
		if(speed < 0.2)
			speed = 0.2;
		double fwd = MC.player.zza;
		if(fwd == 0 && MC.player.xxa != 0)
			fwd = 0.1f;
		double dir = yaw + (MC.player.xxa > 0 ? -Math.PI/2 : 0);
		MC.player.setDeltaMovement(-Math.sin(dir) * speed, v.y,
			Math.cos(dir) * speed);
	}
}
