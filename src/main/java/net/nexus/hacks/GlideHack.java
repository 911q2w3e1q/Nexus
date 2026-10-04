package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class GlideHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("滑翔速度", 0.1f, 0.02f, 0.5f, 0.02f);
	
	public GlideHack()
	{
		super("Glide", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				Vec3 v = MC.player.getDeltaMovement();
		if(!MC.player.onGround() && v.y < -0.1)
			MC.player.setDeltaMovement(v.x, -speed.value, v.z);
	}
}
