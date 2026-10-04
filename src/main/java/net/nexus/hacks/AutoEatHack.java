package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public final class AutoEatHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting hunger = new Setting("饥饿阈值", 12f, 5f, 20f, 1f);
	
	public AutoEatHack()
	{
		super("AutoEat", "其他");
		addSetting(hunger);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.getFoodData().getFoodLevel() >= hunger.value)
			return;
		
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack == null || stack.isEmpty())
				continue;
			FoodProperties food =
				stack.get(net.minecraft.core.component.DataComponents.FOOD);
			if(food == null)
				continue;
			
			MC.player.getInventory().setSelectedSlot(i);
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			return;
		}
	}
}
