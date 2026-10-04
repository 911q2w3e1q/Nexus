package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class FreecamHack extends Hack
{
	private static boolean active;
	private static Vec3 camPos;
	private static Vec3 startPos;
	private static float startYaw;
	private static float startPitch;
	
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("飞行速度", 0.4f, 0.1f, 2f, 0.1f);
	
	public FreecamHack()
	{
		super("Freecam", "移动");
		addSetting(speed);
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static Vec3 getCamPos()
	{
		return camPos;
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
		if(MC.player != null)
		{
			camPos = MC.player.position();
			startPos = MC.player.position();
			startYaw = MC.player.getYRot();
			startPitch = MC.player.getXRot();
		}
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		Vec3 pos = camPos != null ? camPos : MC.player.position();
		float yaw = (float)Math.toRadians(MC.player.getYRot());
		float pitch = (float)Math.toRadians(MC.player.getXRot());
		
		double speed = this.speed.value;
		if(MC.options.keySprint.isDown())
			speed = 1.0;
		
		// 前/后
		double forward = (MC.options.keyUp.isDown() ? 1 : 0)
			- (MC.options.keyDown.isDown() ? 1 : 0);
		// 左/右
		double strafe = (MC.options.keyLeft.isDown() ? 1 : 0)
			- (MC.options.keyRight.isDown() ? 1 : 0);
		
		double vx = 0, vy = 0, vz = 0;
		if(forward != 0)
		{
			vx += -Math.sin(yaw) * forward;
			vz += Math.cos(yaw) * forward;
		}
		if(strafe != 0)
		{
			vx += -Math.sin(yaw - Math.PI / 2) * strafe;
			vz += Math.cos(yaw - Math.PI / 2) * strafe;
		}
		// 垂直
		if(MC.options.keyJump.isDown())
			vy += 1;
		if(MC.options.keyShift.isDown())
			vy -= 1;
		
		double len = Math.sqrt(vx * vx + vz * vz);
		if(len > 0)
		{
			vx = vx / len * speed;
			vz = vz / len * speed;
		}
		
		camPos = pos.add(vx, vy * speed, vz);
	}
}
