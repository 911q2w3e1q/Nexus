package net.nexus.mixin;

import net.nexus.hacks.AntiKnockbackHack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
	@Inject(method = "knockback", at = @At("HEAD"),
		cancellable = true)
	private void nexusAntiKnockback(double strength, double dx,
		double dz, CallbackInfo ci)
	{
		if((Object)this instanceof LocalPlayer
			&& AntiKnockbackHack.shouldBlock())
			ci.cancel();
	}
}
