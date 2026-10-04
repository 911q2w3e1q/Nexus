package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.phys.Vec3;

public final class IceSpeedHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public IceSpeedHack()
	{
		super("IceSpeed", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(MC.level.getBlockState(MC.player.blockPosition().below())
			.getBlock() instanceof IceBlock)
		{
			Vec3 v = MC.player.getDeltaMovement();
			double yaw = Math.toRadians(MC.player.getYRot());
			double speed = Math.sqrt(v.x * v.x + v.z * v.z);
			if(speed < 0.6 && MC.options.keyUp.isDown())
				MC.player.setDeltaMovement(
					-Math.sin(yaw) * 0.6, v.y, Math.cos(yaw) * 0.6);
		}
	}
}
