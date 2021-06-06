package net.gooby.ass.src;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class ClientMessages extends Thread{
	
	protected Minecraft mc = Minecraft.getMinecraft();
	public EntityPlayerSP ep = mc.thePlayer;
	//private final static List clientMessages = Lists.newArrayList();
	
	public void sendPlayerMessage(String input)
	{
		ep.addChatComponentMessage(new ChatComponentText(new String(Mods.clientPrefix + input).replaceAll("&", "§")));
	}
	
	public void run()
	{
		for(int i = 0; i < Integer.MAX_VALUE; i++)
		{
    		Random msgRand = new Random();
    		int randID = msgRand.nextInt(Mods.clientMessages.length - 0) + 0;
        	sendPlayerMessage(Mods.clientMessages[randID]);
        	try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
