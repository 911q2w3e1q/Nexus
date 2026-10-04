package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public final class WTapHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int releaseTicks;
	
	public WTapHack()
	{
		super("WTap", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(MC.options.keyAttack.isDown()
			&& MC.hitResult != null
			&& MC.hitResult.getType()
				== net.minecraft.world.phys.HitResult.Type.ENTITY)
		{
			releaseTicks = 3;
			MC.player.setSprinting(false);
		}
		
		if(releaseTicks > 0)
			releaseTicks--;
	}
}
