package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class CreativeFlightHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("速度", 1f, 0.3f, 3f, 0.1f);
	
	public CreativeFlightHack()
	{
		super("CreativeFlight", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 创造式飞行：水平按输入方向，空格升/蹲降，不碰创造能力
		MC.player.setNoGravity(true);
		float forward = MC.player.zza;
		float strafe = MC.player.xxa;
		float yaw = MC.player.getYRot();
		double sin = Math.sin(Math.toRadians(yaw));
		double cos = Math.cos(Math.toRadians(yaw));
		double base = 0.12 * speed.value;
		double hx = (-sin * forward + cos * strafe) * base;
		double hz = (cos * forward + sin * strafe) * base;
		double vy = 0;
		if(MC.options.keyJump.isDown())
			vy = base;
		else if(MC.options.keyShift.isDown())
			vy = -base;
		MC.player.setDeltaMovement(hx, vy, hz);
	}
	
	@Override
	protected void onDisable()
	{
		if(MC.player != null)
			MC.player.setNoGravity(false);
	}
}
