package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class FlightHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	/** 基础加速度（每 tick 方块/秒系数） */
	private static final double BASE_ACCEL = 0.15;
	/** 阻尼系数：每 tick 速度保留比例 */
	private static final double DAMPING = 0.85;
	/** 悬停阻尼：不输入方向时快速停下 */
	private static final double HOVER_DAMPING = 0.6;
	
	public final Setting speed = new Setting("飞行速度",
		1f, 0.3f, 5f, 0.1f);
	public final Setting glide = new Setting("滑翔模式", false);
	
	public FlightHack()
	{
		super("Flight", "移动");
		addSetting(speed);
		addSetting(glide);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 真实飞行：不碰创造能力，靠速度驱动 + 取消重力
		MC.player.setNoGravity(true);
		
		float forward = MC.player.zza;
		float strafe = MC.player.xxa;
		float yaw = MC.player.getYRot();
		double sin = Math.sin(Math.toRadians(yaw));
		double cos = Math.cos(Math.toRadians(yaw));
		
		double base = BASE_ACCEL * speed.value;
		// 水平：输入方向（WASD 相对视角）
		double hx = (-sin * forward + cos * strafe) * base;
		double hz = (cos * forward + sin * strafe) * base;
		// 垂直：空格上升 / 蹲下下降 / 都不按=悬停
		double vy = 0;
		if(MC.options.keyJump.isDown())
			vy = base;
		else if(MC.options.keyShift.isDown())
			vy = -base;
		
		Vec3 v = MC.player.getDeltaMovement();
		// 有输入时朝目标速度逼近；无输入时按悬停阻尼减速
		double damping = (forward != 0 || strafe != 0
			|| MC.options.keyJump.isDown()
			|| MC.options.keyShift.isDown())
			? DAMPING : HOVER_DAMPING;
		
		// 滑翔模式：保留原有垂直速度（更顺滑的斜向移动）
		if(glide.boolValue)
			vy = v.y * 0.98 + (MC.options.keyJump.isDown()
				? base : (MC.options.keyShift.isDown()
					? -base : 0));
		
		MC.player.setDeltaMovement(
			hx + (v.x - hx) * (1 - damping),
			vy,
			hz + (v.z - hz) * (1 - damping));
	}
	
	@Override
	protected void onDisable()
	{
		if(MC.player != null)
		{
			MC.player.setNoGravity(false);
		}
	}
}
