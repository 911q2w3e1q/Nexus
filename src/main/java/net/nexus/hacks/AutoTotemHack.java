package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
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
		
		// 副手放不死图腾
		if(MC.player.getOffhandItem().is(Items.TOTEM_OF_UNDYING))
			return;
		
		for(int i = 9; i < 36; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && s.is(Items.TOTEM_OF_UNDYING))
			{
				MC.player.setItemSlot(
					net.minecraft.world.entity.EquipmentSlot.OFFHAND,
					s.copy());
				MC.player.getInventory().setItem(i,
					net.minecraft.world.item.ItemStack.EMPTY);
				return;
			}
		}
	}
}
