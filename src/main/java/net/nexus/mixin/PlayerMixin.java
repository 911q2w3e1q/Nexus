package net.nexus.mixin;

import net.nexus.hacks.ReachHack;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin
{
	@Inject(method = "entityInteractionRange", at = @At("RETURN"),
		cancellable = true)
	private void nexusReach(CallbackInfoReturnable<Double> cir)
	{
		if(ReachHack.isActive())
			cir.setReturnValue(ReachHack.getReach());
	}
}
