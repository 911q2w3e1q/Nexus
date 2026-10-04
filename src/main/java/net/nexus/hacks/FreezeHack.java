package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class FreezeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public FreezeHack()
	{
		super("Freeze", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player != null)
			MC.player.setDeltaMovement(0, 0, 0);
	}
}
