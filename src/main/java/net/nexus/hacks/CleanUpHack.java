package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.item.ItemEntity;

public final class CleanUpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 3f, 1f, 10f, 1f);
	
	public CleanUpHack()
	{
		super("CleanUp", "其他");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		for(var e : MC.level.entitiesForRendering())
			if(e instanceof ItemEntity item && MC.player.distanceTo(item) < range.value)
				MC.gameMode.attack(MC.player, item);
	}
}
