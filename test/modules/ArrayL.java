package test.modules;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;

public class ArrayL extends ModuleBase{

	public ArrayL() {
		super("Array List", "Shows a list of all the enabled mods", Keyboard.KEY_UNLABELED, Color.CYAN.getRGB(), Catagory.RENDER);
		// TODO Auto-generated constructor stub
	}
	
	
	public void toggleEvent(){
		Main.instance.screen.arrayIsOpen = this.toggled();
	}


}