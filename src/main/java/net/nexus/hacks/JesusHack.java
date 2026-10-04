package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class JesusHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public JesusHack()
	{
		super("Jesus", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.isInWater() && !MC.player.isSwimming())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0, v.z);
			MC.player.setOnGround(true);
		}
	}
}
