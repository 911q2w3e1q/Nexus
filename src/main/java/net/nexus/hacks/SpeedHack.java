package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class SpeedHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("速度", 1.5f, 1f, 5f, 0.1f);
	public final Setting mode = new Setting("模式",
		new String[] {"直线", "兔子跳"}, 0);
	
	public SpeedHack()
	{
		super("Speed", "移动");
		addSetting(speed);
		addSetting(mode);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 兔子跳模式：落地起跳
		if(mode.getEnumValue().equals("兔子跳"))
		{
			if(MC.player.onGround())
				MC.player.jumpFromGround();
		}
		
		// 沿移动方向加速
		float forward = MC.player.zza;
		float strafe = MC.player.xxa;
		float yaw = MC.player.getYRot();
		double sin = Math.sin(Math.toRadians(yaw));
		double cos = Math.cos(Math.toRadians(yaw));
		double dx = (-sin * forward + cos * strafe) * speed.value;
		double dz = (cos * forward + sin * strafe) * speed.value;
		
		Vec3 v = MC.player.getDeltaMovement();
		if(forward != 0 || strafe != 0)
			MC.player.setDeltaMovement(dx, v.y, dz);
	}
}
