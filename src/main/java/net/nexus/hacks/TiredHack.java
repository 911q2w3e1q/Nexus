package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class TiredHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public TiredHack()
	{
		super("Tired", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 疲倦效果：缓慢减速
		var v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(v.x * 0.9, v.y, v.z * 0.9);
	}
}
