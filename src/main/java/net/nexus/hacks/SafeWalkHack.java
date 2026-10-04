package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class SafeWalkHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public SafeWalkHack()
	{
		super("SafeWalk", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				if(MC.player.onGround())
			MC.player.setOnGround(true);
	}
}
