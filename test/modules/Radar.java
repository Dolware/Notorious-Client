package test.modules;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;

public class Radar extends ModuleBase{

	public Radar() {
		super("Radar", "Shows everyone within the vicinity", Keyboard.KEY_UNLABELED, Color.BLUE.getRGB(), Catagory.RENDER);
		// TODO Auto-generated constructor stub
	}
	
	
	public void toggleEvent(){
		//Main.instance.screen.isRadarEnabled = this.toggled();
	}


}
