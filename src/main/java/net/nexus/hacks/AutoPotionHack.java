package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

public final class AutoPotionHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting hp = new Setting("血量阈值",
		8f, 1f, 16f, 1f);
	
	public AutoPotionHack()
	{
		super("AutoPotion", "战斗");
		addSetting(hp);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 血量低时喝治疗药水，平时喝速度/力量增益
		float health = MC.player.getHealth();
		boolean needHeal = health <= hp.value;
		
		for(int i = 0; i < 36; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s == null || s.isEmpty())
				continue;
			if(!s.is(Items.POTION))
				continue;
			var pc = s.get(DataComponents.POTION_CONTENTS);
			if(pc == null)
				continue;
			boolean good = false;
			for(var inst : pc.getAllEffects())
			{
				var ef = inst.getEffect();
				if(needHeal && ef.is(MobEffects.INSTANT_HEALTH))
					good = true;
				if(!needHeal && (ef.is(MobEffects.SPEED)
					|| ef.is(MobEffects.STRENGTH)))
					good = true;
			}
			if(good)
			{
				MC.player.getInventory().setSelectedSlot(i < 9 ? i : 0);
				MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
				return;
			}
		}
	}
}
