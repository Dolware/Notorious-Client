package net.gooby.ass.src;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class ClientMessagesDoog extends TimerTask{
	
    protected Minecraft mc = Minecraft.getMinecraft();
    public EntityPlayerSP ep = mc.thePlayer;
    public static boolean messagesOn = false;
    public static boolean threadAlreadyStarted = false;
    public static Timer timer = new Timer();

    public void sendPlayerMessage(String input)
    {
            ep.addChatComponentMessage(new ChatComponentText(new String(Mods.clientPrefix + input).replaceAll("&", "§")));
    }

    @Override
    public void run()
    {
            if(messagesOn)
            {
        		Random msgRand = new Random();
        		int randID = msgRand.nextInt(Mods.clientMessages.length - 0) + 0;
            	sendPlayerMessage(Mods.clientMessages[randID]);
            }
    }

    public static void toggle()
    {
            messagesOn = !messagesOn;
    }

    public static void start()
    {
            timer.schedule(new ClientMessagesDoog(), 0, 100);
            threadAlreadyStarted = true;
    }
}
