package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class JetpackHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting power = new Setting("推力", 0.6f, 0.2f, 2f, 0.1f);
	
	public JetpackHack()
	{
		super("Jetpack", "移动");
		addSetting(power);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.options.keyJump.isDown())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, power.value, v.z);
		}
	}
}
