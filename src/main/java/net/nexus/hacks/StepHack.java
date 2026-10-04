package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class StepHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting height = new Setting("高度", 1.25f, 0.5f, 3f, 0.25f);
	
	public StepHack()
	{
		super("Step", "移动");
		addSetting(height);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.player.onGround())
			return;
		
		// 前方有方块且是台阶高度（约1格）时自动跳
		if(MC.options.keyUp.isDown()
			&& MC.level.getBlockState(
				MC.player.blockPosition().offset(0, 1, 0)).isSolid())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0.4, v.z);
		}
	}
}
