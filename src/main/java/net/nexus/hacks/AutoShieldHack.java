package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

public final class AutoShieldHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoShieldHack()
	{
		super("AutoShield", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 手持盾牌时自动举盾
		if(MC.player.getMainHandItem().is(Items.SHIELD))
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
