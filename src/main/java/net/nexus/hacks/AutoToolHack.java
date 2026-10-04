package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class AutoToolHack extends Hack
{
	private static final Item[] PICKS =
		{Items.NETHERITE_PICKAXE, Items.DIAMOND_PICKAXE,
			Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE,
			Items.STONE_PICKAXE, Items.WOODEN_PICKAXE};
	
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoToolHack()
	{
		super("AutoTool", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		if(!(MC.hitResult instanceof net.minecraft.world.phys.BlockHitResult))
			return;
		
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack == null || stack.isEmpty())
				continue;
			for(Item pick : PICKS)
				if(stack.is(pick))
				{
					MC.player.getInventory().setSelectedSlot(i);
					return;
				}
		}
	}
}
