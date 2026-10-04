package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class HighJumpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting height = new Setting("跳跃高度", 1.5f, 0.5f, 5f, 0.25f);
	
	public HighJumpHack()
	{
		super("HighJump", "移动");
		addSetting(height);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
				if(MC.player.onGround() && MC.options.keyJump.isDown())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, height.value, v.z);
		}
	}
}
