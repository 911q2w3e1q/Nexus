package net.nexus.hacks;

public final class PlayerEspHack extends EspHackBase
{
	private static boolean active;
	private static int colorIndex;
	private static float maxDist = 96f;
	
	public final Setting distance = new Setting("显示距离", 96f, 16f, 256f, 8f);
	
	public PlayerEspHack()
	{
		super("PlayerESP", "渲染");
		addSetting(distance);
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static int getColor()
	{
		return COLORS[colorIndex];
	}
	
	public static String getColorName()
	{
		return COLOR_NAMES[colorIndex];
	}
	
	public static float getMaxDist()
	{
		return maxDist;
	}
	
	public static int nextColorIndex()
	{
		colorIndex = (colorIndex + 1) % COLORS.length;
		return colorIndex;
	}
	
	@Override
	public void onTick()
	{
		maxDist = distance.value;
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
		maxDist = distance.value;
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
	}
}
