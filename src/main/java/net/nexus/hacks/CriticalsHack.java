package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class CriticalsHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public CriticalsHack()
	{
		super("Criticals", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.player.onGround())
			return;
		
		tick++;
		if(tick < 20)
			return;
		tick = 0;
		
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(v.x, 0.25, v.z);
	}
}
