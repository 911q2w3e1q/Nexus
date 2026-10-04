package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class AirJumpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AirJumpHack()
	{
		super("AirJump", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				if(MC.options.keyJump.isDown())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0.5, v.z);
		}
	}
}
