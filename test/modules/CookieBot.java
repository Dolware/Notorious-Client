package test.modules;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.Mods;
import net.minecraft.entity.player.EntityPlayerMP;

public class CookieBot extends ModuleBase{
	
	private boolean isMoving;
	private EntityPlayerMP ep;
	private PrintWriter writer;
	
	public CookieBot() {
		super("Cookie Bot", "Watches chat for certain triggers and prints them to a file", Keyboard.KEY_UNLABELED, 0xFF00D8FF, Catagory.MISC);
		//ep = mc.thePlayer;
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
	    	/*Main.instance.cookieTriggers.add("Cookies");
	    	Main.instance.cookieTriggers.add("Cookie");
	    	Main.instance.cookieTriggers.add("Nulling");
	    	Main.instance.cookieTriggers.add("64.94.100.123");
	    	Main.instance.cookieTriggers.add(mc.session.getUsername());*/
			
			Mods.cookieWatch = true;
			mc.thePlayer.sendPlayerMessage("Watching for triggers!");
		}else{
			Mods.cookieWatch = false;
			mc.thePlayer.sendPlayerMessage("Not watching for triggers!");
			try {
				writer = new PrintWriter(System.getenv("APPDATA") + "/.minecraft/Notorious/cookielogs.txt", "UTF-8");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(String line : Main.instance.cookieLogs)
			{
				try{
					writer.println(line);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			writer.close();
		}
	}

	
	
}
