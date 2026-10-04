package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class LongJumpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting distance = new Setting("跳跃距离", 1.5f, 0.5f, 5f, 0.25f);
	
	public LongJumpHack()
	{
		super("LongJump", "移动");
		addSetting(distance);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.options.keyJump.isDown()
			|| !MC.player.onGround())
			return;
		
		double yaw = Math.toRadians(MC.player.getYRot());
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(-Math.sin(yaw) * distance.value, v.y,
			Math.cos(yaw) * distance.value);
	}
}
