package net.gooby.ass.gui;

import java.util.Random;

import net.gooby.ass.src.Mods;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class HandleButtons extends GuiScreen implements Runnable{
	
	private void addButtons()
	{
        this.buttonList.add(new CustomButton(1, this.width / 2 - 180, 97, 70, 20, I18n.format("Singleplayer", new Object[0])));
        this.buttonList.add(new CustomButton(2, this.width / 2 - 108, 97, 70, 20, I18n.format("Multiplayer", new Object[0])));
        this.buttonList.add(new CustomButton(0, this.width / 2 - 36, 97, 70, 20, I18n.format("Options", new Object[0])));
        this.buttonList.add(new CustomButton(4, this.width / 2 + 36, 97, 70, 20, I18n.format("Quit", new Object[0])));
        this.buttonList.add(new CustomButton(1337, this.width / 2 + 108, 97, 70, 20, I18n.format("§6§nVIP Login", new Object[0])));
        this.handle();
	}
	
	private void handle()
	{
		for(Object button : this.buttonList)
		{
			System.out.println("Im gay");
		}
	}
	
	@Override
	public void run()
	{
		this.addButtons();
	}

}
