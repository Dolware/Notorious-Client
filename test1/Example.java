package test1;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import test.modules.ModuleBase;

/**
 * 
 * @author Dyarchy & Dolan
 * 
 * Notorious API module loading.
 * !THIS IS FOR ADVANCED USERS ONLY!
 */

							 //Our module base, for events and what-not.
public class Example extends ModuleBase{

	//Constructor, Module; Name, Description, Bind, Color and type.
	public Example() {
		/*
		 * Catagories: COMBAT, WORLD, PLAYER, RENDER, MISC
		 */
		super("Example Module", "This is an example module", Keyboard.KEY_J, Color.RED.getRGB(), Catagory.MISC);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Event fired when the module is toggled by either GUI, command or keybind
	 * Announcement of the enable/disable of the module is automatic.
	 */
	public void toggleEvent(){
		/*
		 * mc = Minecraft.getMinecraft();
		 * this.toggled() returns if the module is currently enabled or disabled(True/False)
		 * sendPlayerMessage(String message); prints a message to the user with the client prefix
		 */
		if(this.toggled()){
			mc.thePlayer.sendPlayerMessage("Example module enabled!");
		}else{
			mc.thePlayer.sendPlayerMessage("Example module disabled!");

		}
	}
	


}
