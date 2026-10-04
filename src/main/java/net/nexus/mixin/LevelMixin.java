package net.nexus.mixin;

import net.nexus.hacks.XRayHack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin
{
	@Inject(method = "getBlockState", at = @At("RETURN"),
		cancellable = true)
	private void nexusXRay(BlockPos pos,
		CallbackInfoReturnable<BlockState> cir)
	{
		if(!((Object)this instanceof ClientLevel))
			return;
		
		if(net.nexus.hacks.WallHackHack.isActive())
		{
			BlockState state = cir.getReturnValue();
			if(!net.nexus.hacks.WallHackHack.shouldShow(state))
				cir.setReturnValue(Blocks.AIR.defaultBlockState());
			return;
		}
		
		if(XRayHack.isActive())
		{
			BlockState state = cir.getReturnValue();
			if(!XRayHack.shouldShow(state))
				cir.setReturnValue(Blocks.AIR.defaultBlockState());
		}
	}
}
