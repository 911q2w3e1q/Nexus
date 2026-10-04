package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public final class PlayerAuraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting range = new Setting("范围", 4.25f, 2f, 8f, 0.25f);
	
	public PlayerAuraHack()
	{
		super("PlayerAura", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double best = range.value;
		for(Entity e : net.nexus.util.EntityUtils.getAttackableEntities(
			true, false))
		{
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
