package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class BunnyHopHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("速度", 0.4f, 0.2f, 2f, 0.1f);
	
	public BunnyHopHack()
	{
		super("BunnyHop", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				if(!MC.player.onGround() || !MC.options.keyJump.isDown())
			return;
		Vec3 v = MC.player.getDeltaMovement();
		double yaw = Math.toRadians(MC.player.getYRot());
		double speed = Math.sqrt(v.x * v.x + v.z * v.z);
		if(speed < this.speed.value)
			speed = this.speed.value;
		MC.player.setDeltaMovement(-Math.sin(yaw) * speed, v.y,
			Math.cos(yaw) * speed);
	}
}
