package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;

public final class FastBreakHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int counter;
	
	public final Setting multiplier = new Setting("倍率", 2f, 1f, 10f, 1f);
	
	public FastBreakHack()
	{
		super("FastBreak", "世界");
		addSetting(multiplier);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(MC.options.keyAttack.isDown()
			&& MC.hitResult instanceof BlockHitResult hit)
		{
			counter++;
			if(counter % (int)multiplier.value == 0)
				MC.gameMode.destroyBlock(hit.getBlockPos());
		}
	}
}
