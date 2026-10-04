package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class SpammerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int counter;
	
	public final Setting interval = new Setting("间隔", 40f, 10f, 200f, 10f);
	
	public SpammerHack()
	{
		super("Spammer", "聊天");
		addSetting(interval);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.getConnection() == null)
			return;
		
		counter++;
		if(counter < (int)interval.value)
			return;
		counter = 0;
		
		MC.getConnection().sendChat("Nexus Client!");
	}
}
