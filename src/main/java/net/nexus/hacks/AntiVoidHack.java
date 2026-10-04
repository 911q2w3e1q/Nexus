package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class AntiVoidHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private Vec3 lastSafePos;
	
	public AntiVoidHack()
	{
		super("AntiVoid", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 记录最后安全位置（脚下有方块或在地面）
		if(MC.player.onGround()
			|| !MC.level.getBlockState(
				MC.player.blockPosition().below()).isAir())
		{
			lastSafePos = MC.player.position();
		}
		
		// 掉进虚空（Y 过低且脚下无方块）：拉回安全位置
		if(MC.player.getY() < -30 && lastSafePos != null)
		{
			MC.player.setPos(lastSafePos.x, lastSafePos.y,
				lastSafePos.z);
			MC.player.setDeltaMovement(0, 0, 0);
		}
	}
}
