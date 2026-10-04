package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class FlightHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("飞行速度", 1f, 0.3f, 5f, 0.1f);
	
	public FlightHack()
	{
		super("Flight", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		MC.player.getAbilities().flying = true;
		double speed = 0.05 * this.speed.value;
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(v.x * 0.9, v.y * 0.9, v.z * 0.9);
		
		if(MC.options.keyJump.isDown())
			MC.player.setDeltaMovement(v.x, speed, v.z);
		if(MC.options.keyShift.isDown())
			MC.player.setDeltaMovement(v.x, -speed, v.z);
	}
	
	@Override
	protected void onDisable()
	{
		if(MC.player != null)
			MC.player.getAbilities().flying = false;
	}
}
