package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Items;

public final class AutoFishHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean reeling;
	private int counter;
	
	public AutoFishHack()
	{
		super("AutoFish", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.FISHING_ROD))
			return;
		
		boolean hook = false;
		for(var e : MC.level.entitiesForRendering())
			if(e instanceof FishingHook f
				&& f.getOwner() == MC.player)
			{
				hook = true;
				if(f.getDeltaMovement().y < -0.01)
				{
					MC.gameMode.useItem(MC.player,
						InteractionHand.MAIN_HAND);
					counter = 20;
					reeling = true;
				}
			}
		
		if(!hook && reeling)
		{
			reeling = false;
			counter = 0;
		}
		
		if(!hook && counter == 0)
		{
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			counter = 5;
		}
	}
}
