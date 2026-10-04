package net.nexus.hacks;

import net.nexus.hack.Hack;

/** ESP 基类：颜色可自定义（右键点击 GUI 行切换） */
public abstract class EspHackBase extends Hack
{
	protected static final int[] COLORS = {
		0xFFFFFFFF, // 白
		0xFFFF0000, // 红
		0xFF00FF00, // 绿
		0xFF00AAFF, // 蓝
		0xFFFFFF00, // 黄
		0xFFFF00FF, // 紫
		0xFF00FFFF, // 青
		0xFFFF8800, // 橙
	};
	
	protected static final String[] COLOR_NAMES = {
		"白", "红", "绿", "蓝", "黄", "紫", "青", "橙"
	};
	
	private static boolean anyActive;
	
	protected EspHackBase(String name, String category)
	{
		super(name, category);
	}
	
	/** 切换到下一个颜色，返回新颜色 */
	public int nextColor()
	{
		return 0;
	}
	
	public static void setAnyActive(boolean active)
	{
		anyActive = active;
	}
	
	public static boolean isAnyActive()
	{
		return anyActive;
	}
}
