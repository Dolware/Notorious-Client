package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class DayTime extends ModuleBase{

	 public DayTime() {
	  super("Constant Day", "Sets the client time to be constant day", KeyBindings.DAYTIME, 0, Catagory.WORLD);
	  // TODO Auto-generated constructor stub
	 }
	 public void toggleEvent(){
	  if(this.toggled()){
		 // mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
	  }else{
		//  mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
	  }
	 }
	 
	 public void tick(){
	  this.mc.theWorld.setWorldTime(1000);
	 }

}