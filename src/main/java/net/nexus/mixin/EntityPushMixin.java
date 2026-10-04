package net.nexus.mixin;

import net.nexus.hacks.AntiEntityPushHack;
import net.nexus.hacks.AntiWaterPushHack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityPushMixin
{
	// 防实体推挤：实体碰撞推挤
	@Inject(method = "push(Lnet/minecraft/world/entity/Entity;)V",
		at = @At("HEAD"), cancellable = true)
	private void nexusNoEntityPush(Entity other, CallbackInfo ci)
	{
		if((Object)this instanceof LocalPlayer
			&& AntiEntityPushHack.shouldBlockEntity(other))
			ci.cancel();
	}
	
	// 防水流推挤
	@Inject(method = "push(DDD)V",
		at = @At("HEAD"), cancellable = true)
	private void nexusNoWaterPush(double x, double y, double z,
		CallbackInfo ci)
	{
		if((Object)this instanceof LocalPlayer
			&& AntiWaterPushHack.shouldBlockWater())
			ci.cancel();
	}
	
	// 防击退也拦 push(Vec3)（部分击退走 Vec3 重载）
	@Inject(method = "push(Lnet/minecraft/world/phys/Vec3;)V",
		at = @At("HEAD"), cancellable = true)
	private void nexusNoVec3Push(net.minecraft.world.phys.Vec3 v,
		CallbackInfo ci)
	{
		if((Object)this instanceof LocalPlayer
			&& AntiEntityPushHack.shouldBlockEntity((Entity)(Object)this))
			ci.cancel();
	}
}
