package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class NoRain extends ModuleBase{

	 public NoRain() {
		  super("No Rain", "Changes the client weather to be as clear as tetro's jizz", KeyBindings.NORAIN, 0, Catagory.WORLD);
	 }
	 
	 public void toggleEvent(){
		  if(this.toggled()){
			 // mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
		  }else{
			 // mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
	    }
	 }
	
	 public void tick(){
		  this.mc.theWorld.setThunderStrength(0);
		  this.mc.theWorld.setRainStrength(0);
	 }
}
