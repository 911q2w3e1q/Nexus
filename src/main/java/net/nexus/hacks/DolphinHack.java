package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class DolphinHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("游泳速度", 0.7f, 0.3f, 2f, 0.1f);
	
	public DolphinHack()
	{
		super("Dolphin", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.isInWater())
		{
			Vec3 v = MC.player.getDeltaMovement();
			double yaw = Math.toRadians(MC.player.getYRot());
			if(MC.options.keyUp.isDown())
				MC.player.setDeltaMovement(
					-Math.sin(yaw) * speed.value, 0.3, Math.cos(yaw) * speed.value);
		}
	}
}
