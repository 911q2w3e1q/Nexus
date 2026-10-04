package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class NoSlowDownHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoSlowDownHack()
	{
		super("NoSlowDown", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				Vec3 v = MC.player.getDeltaMovement();
		double yaw = Math.toRadians(MC.player.getYRot());
		double speed = 0.3;
		if(MC.options.keyUp.isDown())
			MC.player.setDeltaMovement(-Math.sin(yaw) * speed, v.y,
				Math.cos(yaw) * speed);
	}
}
