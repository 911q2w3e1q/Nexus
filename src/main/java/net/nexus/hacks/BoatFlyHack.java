package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.vehicle.boat.Boat;

public final class BoatFlyHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("速度", 1f, 0.5f, 3f, 0.1f);
	
	public BoatFlyHack()
	{
		super("BoatFly", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		var vehicle = MC.player.getVehicle();
		if(!(vehicle instanceof Boat))
			return;
		
		// 船悬浮控制
		vehicle.setNoGravity(true);
		if(MC.options.keyJump.isDown())
		{
			vehicle.setDeltaMovement(
				vehicle.getDeltaMovement().x, speed.value,
				vehicle.getDeltaMovement().z);
		}
		if(MC.options.keyShift.isDown())
		{
			vehicle.setDeltaMovement(
				vehicle.getDeltaMovement().x, -speed.value,
				vehicle.getDeltaMovement().z);
		}
	}
}
