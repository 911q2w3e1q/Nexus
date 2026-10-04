package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

public final class AutoPearlHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting health = new Setting("血量阈值", 8f, 1f, 20f, 1f);
	
	public AutoPearlHack()
	{
		super("AutoPearl", "战斗");
		addSetting(health);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 血量低时扔末影珍珠逃生
		if(MC.player.getHealth() > health.value)
			return;
		
		int slot = -1;
		for(int i = 0; i < 9; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && s.is(Items.ENDER_PEARL))
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		MC.player.getInventory().setSelectedSlot(slot);
		MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
