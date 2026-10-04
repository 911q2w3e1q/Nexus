package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class AutoSwordHack extends Hack
{
	private static final Item[] SWORDS =
		{Items.NETHERITE_SWORD, Items.DIAMOND_SWORD, Items.IRON_SWORD,
			Items.GOLDEN_SWORD, Items.STONE_SWORD, Items.WOODEN_SWORD};
	
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoSwordHack()
	{
		super("AutoSword", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		int bestSlot = -1;
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack == null || stack.isEmpty())
				continue;
			for(Item sword : SWORDS)
				if(stack.is(sword))
				{
					bestSlot = i;
					break;
				}
			if(bestSlot >= 0)
				break;
		}
		
		if(bestSlot >= 0
			&& MC.player.getInventory().getSelectedSlot() != bestSlot)
			MC.player.getInventory().setSelectedSlot(bestSlot);
	}
}
