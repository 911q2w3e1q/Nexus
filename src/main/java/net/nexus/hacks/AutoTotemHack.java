package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class AutoTotemHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoTotemHack()
	{
		super("AutoTotem", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.getOffhandItem().is(Items.TOTEM_OF_UNDYING))
			return;
		
		for(int i = 0; i < 36; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
			if(stack != null && stack.is(Items.TOTEM_OF_UNDYING))
			{
				MC.player.getInventory().setItem(40, stack.copy());
				MC.player.getInventory().setItem(i, ItemStack.EMPTY);
				return;
			}
		}
	}
}
