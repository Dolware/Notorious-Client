package net.gooby.ass.src;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class HandleConsole {
	//static Minecraft mc;
	
	public static String cmdPrefix = "!";
	
	public static String[] validCommands =
		   {"help",
			"ch",
			"clientmsg",
			"keys",
			"dox",
			"commands",
			"resolve",
			"nh",
			"clear",
			"clip",
			"friend",
			"enemy",
			"debug",
			"mimic",
			"spinbot",
			"name",
			"loadplugin"
		   };
	
	/*public static void PrintHelp(String input)
	{
		if(input == "chat")
		{
			mc.thePlayer.addChatComponentMessage(new ChatComponentText("testing"));
		}
	}*/

}
