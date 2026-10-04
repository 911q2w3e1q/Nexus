package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class NoPumpkinHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoPumpkinHack()
	{
		super("NoPumpkin", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		ItemStack helmet = MC.player.getItemBySlot(EquipmentSlot.HEAD);
		if(helmet.is(Items.CARVED_PUMPKIN))
			MC.player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
	}
}
