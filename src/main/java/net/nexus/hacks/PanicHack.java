package net.nexus.hacks;

import net.nexus.NexusClient;
import net.nexus.hack.Hack;
import net.nexus.hack.HackList;

public final class PanicHack extends Hack
{
	public PanicHack()
	{
		super("Panic", "其他");
	}
	
	@Override
	protected void onEnable()
	{
		HackList hax = NexusClient.getInstance().hackList;
		for(Hack hack : hax.getAll())
		{
			if(hack == this)
				continue;
			hack.setEnabled(false);
		}
	}
}
