package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class KillPotionHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int potionTick;
	
	public final Setting range = new Setting("范围", 5f, 2f, 10f, 0.5f);
	
	public KillPotionHack()
	{
		super("KillPotion", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		Entity target = null;
		double best = range.value;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			double d = EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		if(target == null)
			return;
		
		// 投掷伤害/迟缓药水
		int slot = findPotion();
		if(slot < 0)
			return;
		
		potionTick++;
		if(potionTick > 15)
		{
			potionTick = 0;
			EntityUtils.lookAt(target);
			MC.player.getInventory().setSelectedSlot(slot);
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
		}
	}
	
	private int findPotion()
	{
		for(int i = 0; i < 9; i++)
		{
			ItemStack s = MC.player.getInventory().getItem(i);
			if(s == null || s.isEmpty())
				continue;
			if(!s.is(Items.SPLASH_POTION)
				&& !s.is(Items.LINGERING_POTION))
				continue;
			// 伤害/剧毒/迟缓类药水
			var contents = s.get(
				net.minecraft.core.component.DataComponents
					.POTION_CONTENTS);
			if(contents != null)
			{
				for(var inst : contents.getAllEffects())
				{
					var ef = inst.getEffect();
					if(ef.is(MobEffects.INSTANT_DAMAGE)
						|| ef.is(MobEffects.POISON)
						|| ef.is(MobEffects.SLOWNESS))
						return i;
				}
			}
		}
		return -1;
	}
}
