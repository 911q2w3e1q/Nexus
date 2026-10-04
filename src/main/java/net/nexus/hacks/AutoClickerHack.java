package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;

public final class AutoClickerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int counter;
	
	public final Setting cps = new Setting("CPS", 8f, 1f, 20f, 1f);
	
	public AutoClickerHack()
	{
		super("AutoClicker", "战斗");
		addSetting(cps);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.options.keyAttack.isDown())
			return;
		
		counter++;
		if(counter < (int)(20 / cps.value))
			return;
		counter = 0;
		
		if(MC.hitResult instanceof EntityHitResult hit)
		{
			MC.gameMode.attack(MC.player, hit.getEntity());
			MC.player.swing(InteractionHand.MAIN_HAND);
		}
	}
}
