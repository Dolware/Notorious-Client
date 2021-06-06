package test.modules;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;

public class PlayerInfo extends ModuleBase{

	public PlayerInfo() {
		super("Player Info", "Shows information of the current player", Keyboard.KEY_UNLABELED, Color.CYAN.getRGB(), Catagory.MISC);
		// TODO Auto-generated constructor stub
	}
	
	
	public void toggleEvent(){
		//Main.instance.screen.isInfoEnabled = this.toggled();
	}


}