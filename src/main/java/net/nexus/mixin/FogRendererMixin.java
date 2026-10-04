package net.nexus.mixin;

import net.nexus.hacks.NoFogHack;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin
{
	@Inject(method = "setupFog", at = @At("RETURN"),
		cancellable = true)
	private void nexusNoFog(Camera camera, int a, DeltaTracker delta,
		float b, ClientLevel level,
		CallbackInfoReturnable<Vector4f> cir)
	{
		NoFogHack.clearFog(cir.getReturnValue());
	}
}
