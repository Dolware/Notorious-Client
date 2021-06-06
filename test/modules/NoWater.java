package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.src.Mods;

public class NoWater extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public NoWater() {
		super("No Water", "Allows you to no longer be pushed by water", KeyBindings.NOWATER, 0, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			//mc.thePlayer.sendPlayerMessage("Enabled 'No Water'");
			this.nowaterOn = true;
			
		}else{
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
			//mc.thePlayer.sendPlayerMessage("Disabled 'No Water'");
			this.nowaterOn = false;
		}
	}
	
}
