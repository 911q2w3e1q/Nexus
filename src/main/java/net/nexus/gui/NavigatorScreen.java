package net.nexus.gui;

import net.nexus.NexusClient;
import net.nexus.hack.Hack;
import net.nexus.util.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public final class NavigatorScreen extends Screen
{
	private final Minecraft MC = Minecraft.getInstance();
	private EditBox searchBox;
	private String query = "";
	
	public NavigatorScreen()
	{
		super(Component.literal("Nexus Navigator"));
	}
	
	@Override
	protected void init()
	{
		searchBox = new EditBox(MC.font, width / 2 - 110, 40, 220, 16,
			Component.literal("搜索"));
		searchBox.setValue(query);
		searchBox.setResponder(s -> query = s.toLowerCase());
		searchBox.setFocused(true);
		addWidget(searchBox);
	}
	
	private List<Hack> getMatches()
	{
		List<Hack> matches = new ArrayList<>();
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
		{
			String name = Translate.name(hack.getName()).toLowerCase();
			String enName = hack.getName().toLowerCase();
			if(query.isEmpty() || name.contains(query)
				|| enName.contains(query))
				matches.add(hack);
		}
		return matches;
	}
	
	@Override
	public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick)
	{
		g.fill(0, 0, width, height, 0xAA000000);
		
		// 标题
		g.drawString(MC.font, "Nexus Navigator - 搜索功能",
			width / 2 - 70, 20, 0xFFFFFFFF, false);
		
		// 搜索框
		if(searchBox != null)
			searchBox.render(g, mouseX, mouseY, partialTick);
		
		// 结果列表
		int y = 70;
		int index = 0;
		for(Hack hack : getMatches())
		{
			boolean hovered = mouseX >= width / 2 - 120
				&& mouseX <= width / 2 + 120
				&& mouseY >= y && mouseY <= y + 12;
			if(hovered)
				g.fill(width / 2 - 120, y, width / 2 + 120, y + 12,
					0x663366FF);
			
			int color = hack.isEnabled()
				? 0xFF00FF00 : 0xFFFFFFFF;
			g.drawString(MC.font, Translate.name(hack.getName()),
				width / 2 - 116, y + 2, color, false);
			y += 14;
			index++;
			if(y > height - 20 || index > 25)
				break;
		}
		
		super.render(g, mouseX, mouseY, partialTick);
	}
	
	@Override
	public boolean mouseClicked(
		net.minecraft.client.input.MouseButtonEvent event, boolean bl)
	{
		double mouseX = event.x();
		double mouseY = event.y();
		
		int y = 70;
		int index = 0;
		for(Hack hack : getMatches())
		{
			if(mouseX >= width / 2 - 120 && mouseX <= width / 2 + 120
				&& mouseY >= y && mouseY <= y + 12)
			{
				hack.toggle();
				return true;
			}
			y += 14;
			index++;
			if(y > height - 20 || index > 25)
				break;
		}
		
		return super.mouseClicked(event, bl);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
