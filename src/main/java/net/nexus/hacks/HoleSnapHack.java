package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public final class HoleSnapHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public HoleSnapHack()
	{
		super("HoleSnap", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(MC.player.onGround() || MC.player.getAbilities().flying)
			return;
		
		BlockPos pos = MC.player.blockPosition();
		if(!MC.level.getBlockState(pos.below()).isAir())
			return;
		
		Vec3 v = MC.player.getDeltaMovement();
		MC.player.setDeltaMovement(0, v.y, 0);
	}
}
