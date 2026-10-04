package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

public final class AutoArmorHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoArmorHack()
	{
		super("AutoArmor", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		for(int i = 0; i < 36; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack == null || stack.isEmpty())
				continue;
			
			Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
			if(equippable == null)
				continue;
			
			EquipmentSlot slot = equippable.slot();
			ItemStack current = MC.player.getItemBySlot(slot);
			if(current == null || current.isEmpty())
			{
				MC.player.setItemSlot(slot, stack.copy());
				MC.player.getInventory().setItem(i, ItemStack.EMPTY);
				return;
			}
		}
	}
}
