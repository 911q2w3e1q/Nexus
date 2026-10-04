package net.nexus.mixin;

import net.nexus.hacks.FreecamHack;
import net.minecraft.client.Camera;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin
{
	@Shadow @Mutable private Vec3 position;
	
	@Inject(method = "setup", at = @At("TAIL"))
	private void nexusFreecam(Level level, Entity entity,
		boolean detached, boolean thirdPerson, float partialTick,
		CallbackInfo ci)
	{
		if(FreecamHack.isActive())
		{
			Vec3 pos = FreecamHack.getCamPos();
			if(pos != null)
				position = pos;
		}
	}
}
