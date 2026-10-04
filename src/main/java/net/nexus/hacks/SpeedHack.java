package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class SpeedHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting multiplier = new Setting("倍率", 2f, 1f, 10f, 0.5f);
	
	public SpeedHack()
	{
		super("Speed", "移动");
		addSetting(multiplier);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.player.onGround())
			return;
		
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(v.x * multiplier.value, v.y, v.z * multiplier.value);
	}
}
