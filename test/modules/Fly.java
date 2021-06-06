package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.gui.Console;
import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class Fly extends ModuleBase{
	
	public Fly() {
		super("Fly", "Lets you become a G6", KeyBindings.FLY, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			Main.instance.fly_enabled = true;
			/*mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			mc.thePlayer.jump();
			mc.thePlayer.capabilities.setFlySpeed(0.06F);
			mc.thePlayer.capabilities.isFlying = true;*/
		}else{
			/*mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
			mc.thePlayer.capabilities.setFlySpeed(0.04F);
			mc.thePlayer.capabilities.isFlying = false;*/
			Main.instance.fly_enabled = false;
		}
	}
	
	public void onUpdate()
	{
		
	}

	
	
}
