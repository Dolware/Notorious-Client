package test.modules;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class FastMine extends ModuleBase{

	 public FastMine() {
	  super("Fast Mine", "Allows you to mine blocks twice as fast as the normal speed", KeyBindings.FASTMINE, Color.cyan.getRGB(), Catagory.PLAYER);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public void toggleEvent()
	 {
		 if(this.toggled())
		 {
			// mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
		 }else{
			// mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		 }
	 }
	 
	 public void tick(){
	  if(this.toggled()){
	   mc.playerController.blockHitDelay = 0;
	   mc.playerController.curBlockDamageMP = mc.playerController.curBlockDamageMP * 3;
	   }
	 }
}
