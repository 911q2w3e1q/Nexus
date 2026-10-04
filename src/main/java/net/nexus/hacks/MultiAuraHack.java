package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public final class MultiAuraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting count = new Setting("目标数", 3f, 1f, 10f, 1f);
	public final Setting range = new Setting("范围", 4.25f, 2f, 8f, 0.25f);
	
	public MultiAuraHack()
	{
		super("MultiAura", "战斗");
		addSetting(count);
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		int count = 0;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			if(!(e instanceof Monster) && !(e instanceof Player))
				continue;
			if(MC.player.distanceTo(e) > range.value)
				continue;
			
			MC.gameMode.attack(MC.player, e);
			MC.player.swing(InteractionHand.MAIN_HAND);
			count++;
			if(count >= 3)
				break;
		}
	}
}
