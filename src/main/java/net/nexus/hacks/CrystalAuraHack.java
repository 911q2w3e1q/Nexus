package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;

public final class CrystalAuraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 6f, 3f, 10f, 0.5f);
	
	public CrystalAuraHack()
	{
		super("CrystalAura", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double best = range.value;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(!(e instanceof EndCrystal) || !e.isAlive())
				continue;
			double d = net.nexus.util.EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		
		if(target != null)
		{
			net.nexus.util.EntityUtils.lookAt(target);
			MC.gameMode.attack(MC.player, target);
			MC.player.swing(InteractionHand.MAIN_HAND);
		}
	}
}
