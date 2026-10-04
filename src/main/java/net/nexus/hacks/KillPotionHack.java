package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;

public final class KillPotionHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public KillPotionHack()
	{
		super("KillPotion", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.hasEffect(MobEffects.STRENGTH))
			return;
		
		for(int i = 0; i < 9; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
			if(stack != null && stack.is(Items.POTION))
			{
				MC.player.getInventory().setSelectedSlot(i);
				MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
				return;
			}
		}
	}
}
