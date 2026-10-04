package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.ChestMenu;

public final class ChestStealerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting delay = new Setting("拿取延迟", 1f, 1f, 10f, 1f);
	
	public ChestStealerHack()
	{
		super("ChestStealer", "世界");
		addSetting(delay);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.containerMenu instanceof ChestMenu menu)
		{
			for(int i = 0; i < menu.getContainer().getContainerSize(); i++)
			{
				var stack = menu.getContainer().getItem(i);
				if(stack == null || stack.isEmpty())
					continue;
				// 快速移动到玩家背包
				MC.player.getInventory().setItem(
					i % 36,
					stack.copy());
				menu.getContainer().setItem(i,
					net.minecraft.world.item.ItemStack.EMPTY);
				return;
			}
		}
	}
}
