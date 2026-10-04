package net.nexus.gui;

import net.nexus.NexusClient;
import net.nexus.hack.Hack;
import net.nexus.util.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ClickGuiScreen extends Screen
{
	private static final String[] CATEGORIES =
		{"移动", "战斗", "世界", "渲染", "聊天", "其他"};
	
	private String selectedCategory = "移动";
	private int scrollY;
	private final Minecraft MC = Minecraft.getInstance();
	
	public ClickGuiScreen()
	{
		super(Component.literal("Nexus ClickGUI"));
	}
	
	@Override
	public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick)
	{
		// 背景
		// GUI 无背景：开启时背景全透明
		if(!net.nexus.hacks.NoBackgroundHack.isActive())
			g.fill(0, 0, width, height, 0xAA000000);
		
		// 顶部标题
		g.drawString(MC.font, "Nexus Client a0.7 1.21.11",
			width / 2 - 40, 2, 0xFFFFFF);
		
		// 分类栏
		int x = 4;
		for(String cat : CATEGORIES)
		{
			int w = MC.font.width(cat) + 12;
			boolean selected = cat.equals(selectedCategory);
			// 彩虹UI：开启时选中分类用彩虹色
			int selColor = net.nexus.hacks.RainbowUiHack.isActive()
				? net.nexus.hacks.RainbowUiHack.getColor()
				: 0xFF3366FF;
			g.fill(x, 16, x + w, 30, selected ? selColor : 0xFF333333);
			g.drawString(MC.font, cat, x + 6, 19,
				selected ? 0xFFFFFFFF : 0xFFAAAAAA);
			x += w + 4;
		}
		
		// 功能列表（支持滚动）
		int y = 38 - scrollY;
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
		{
			if(!hack.getCategory().equals(selectedCategory))
				continue;
			
			boolean hovered = mouseX >= 4 && mouseX <= width - 4
				&& mouseY >= y && mouseY <= y + 12;
			g.fill(4, y, width - 4, y + 12,
				hovered ? 0x663366FF : 0x33000000);
			
			int color = hack.isEnabled() ? 0xFF00FF00 : 0xFFFFFFFF;
			g.drawString(MC.font, Translate.name(hack.getName()), 8, y + 2,
				color);
			
			// ESP 行右侧显示颜色名（右键切换）
			String colorName = null;
			if(hack instanceof net.nexus.hacks.PlayerEspHack)
				colorName = net.nexus.hacks.PlayerEspHack.getColorName();
			else if(hack instanceof net.nexus.hacks.MobEspHack)
				colorName = net.nexus.hacks.MobEspHack.getColorName();
			else if(hack instanceof net.nexus.hacks.ChestEspHack)
				colorName = net.nexus.hacks.ChestEspHack.getColorName();
			else if(hack instanceof net.nexus.hacks.ItemEspHack)
				colorName = net.nexus.hacks.ItemEspHack.getColorName();
			
			if(colorName != null)
				g.drawString(MC.font, "[" + colorName + "]",
					width - 8 - MC.font.width("[" + colorName + "]"),
					y + 2, 0xFFAAAAAA);
			
			// 有设置的行显示 ⚙ 提示
			if(hack.hasSettings())
				g.drawString(MC.font, "⚙",
					width - 30, y + 2, 0xFFCCCCCC);
			y += 14;
		}
		
		// 滚动条（右侧可拖）
		int total = listHeight();
		int view = height - 50;
		if(total > view)
		{
			int barH = Math.max(16, view * view / total);
			int barY = 38 + (view - barH) * scrollY
				/ (total - view);
			g.fill(width - 3, barY, width - 1, barY + barH,
				0xFF666666);
		}
		
		// 底部提示
		g.drawString(MC.font, "左键开关 | 右键设置 | 滚轮滚动",
			4, height - 10, 0xFF888888);
	}
	
	private int listHeight()
	{
		int count = 0;
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
			if(hack.getCategory().equals(selectedCategory))
				count++;
		return count * 14;
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY,
		double horizontalAmount, double verticalAmount)
	{
		int total = listHeight();
		int view = height - 50;
		if(total > view)
		{
			scrollY += (int)(-verticalAmount * 20);
			if(scrollY < 0)
				scrollY = 0;
			int maxScroll = total - view;
			if(scrollY > maxScroll)
				scrollY = maxScroll;
		}
		return true;
	}
	
	@Override
	public boolean keyPressed(KeyEvent event)
	{
		if(event.key() == 265)
		{
			scrollY = Math.max(0, scrollY - 14);
			return true;
		}
		if(event.key() == 264)
		{
			scrollY += 14;
			return true;
		}
		if(event.key() == 256)
		{
			MC.setScreen(null);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean mouseClicked(
		net.minecraft.client.input.MouseButtonEvent event, boolean bl)
	{
		double mouseX = event.x();
		double mouseY = event.y();
		
		// 分类切换
		int x = 4;
		for(String cat : CATEGORIES)
		{
			int w = MC.font.width(cat) + 12;
			if(mouseY >= 16 && mouseY <= 30 && mouseX >= x
				&& mouseX <= x + w)
			{
				selectedCategory = cat;
				return true;
			}
			x += w + 4;
		}
		
		// 功能开关（滚动偏移）
		int y = 38 - scrollY;
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
		{
			if(!hack.getCategory().equals(selectedCategory))
			{
				continue;
			}
			if(mouseY >= y && mouseY <= y + 12 && mouseX >= 4
				&& mouseX <= width - 4)
			{
				// 右键：ESP 切换颜色 / 有设置的功能打开设置面板
				if(event.button() == 1)
				{
					if(hack instanceof net.nexus.hacks.EspHackBase)
					{
						if(hack instanceof net.nexus.hacks.PlayerEspHack)
							net.nexus.hacks.PlayerEspHack.nextColorIndex();
						else if(hack instanceof net.nexus.hacks.MobEspHack)
							net.nexus.hacks.MobEspHack.nextColorIndex();
						else if(hack instanceof net.nexus.hacks.ChestEspHack)
							net.nexus.hacks.ChestEspHack.nextColorIndex();
						else if(hack instanceof net.nexus.hacks.ItemEspHack)
							net.nexus.hacks.ItemEspHack.nextColorIndex();
						return true;
					}
					if(hack.hasSettings())
					{
						MC.setScreen(new SettingsScreen(hack));
						return true;
					}
				}
				
				hack.toggle();
				return true;
			}
			y += 14;
		}
		
		return super.mouseClicked(event, bl);
	}
	
	@Override
	public boolean mouseDragged(
		net.minecraft.client.input.MouseButtonEvent event,
		double deltaX, double deltaY)
	{
		// 在滚动条区域拖动
		if(event.x() >= width - 8)
		{
			int total = listHeight();
			int view = height - 50;
			if(total > view)
			{
				int barH = Math.max(16, view * view / total);
				int maxScroll = total - view;
				int trackH = view - barH;
				float ratio = (float)((event.y() - 38)
					/ (double)trackH);
				if(ratio < 0)
					ratio = 0;
				if(ratio > 1)
					ratio = 1;
				scrollY = (int)(ratio * maxScroll);
			}
			return true;
		}
		return super.mouseDragged(event, deltaX, deltaY);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
