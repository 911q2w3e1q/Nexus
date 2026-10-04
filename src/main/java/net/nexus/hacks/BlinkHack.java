package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class BlinkHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private Vec3 savedPos;
	private Vec3 targetPos;
	
	public BlinkHack()
	{
		super("Blink", "移动");
	}
	
	@Override
	protected void onEnable()
	{
		if(MC.player != null)
		{
			savedPos = MC.player.position();
			targetPos = null;
		}
	}
	
	@Override
	protected void onDisable()
	{
		// 关闭时瞬移到累积位置
		if(MC.player != null && targetPos != null)
		{
			MC.player.setPos(targetPos.x, targetPos.y, targetPos.z);
		}
		savedPos = null;
		targetPos = null;
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 累积玩家想要移动到的位置，但保持原地（服务器看到原处）
		Vec3 v = MC.player.getDeltaMovement();
		double nx = savedPos.x + v.x;
		double ny = savedPos.y + v.y;
		double nz = savedPos.z + v.z;
		targetPos = new Vec3(nx, ny, nz);
		
		MC.player.setPos(savedPos.x, savedPos.y, savedPos.z);
		MC.player.setDeltaMovement(0, 0, 0);
	}
}
