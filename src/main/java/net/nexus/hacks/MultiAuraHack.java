package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

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
		
		int hit = 0;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			if(EntityUtils.getDistanceTo(e) > range.value)
				continue;
			EntityUtils.lookAt(e);
			MC.gameMode.attack(MC.player, e);
			MC.player.swing(InteractionHand.MAIN_HAND);
			hit++;
			if(hit >= (int)count.value)
				break;
		}
	}
}
