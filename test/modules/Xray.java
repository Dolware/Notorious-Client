package test.modules;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.client.Minecraft;

public class Xray extends ModuleBase{

	 public Xray() {
	  super("Xray", "Wallhack for blocks", KeyBindings.XRAY, Color.red.getRGB(), Catagory.WORLD);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public void toggleEvent(){
	  if(this.toggled()){
		  //mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
	  }else{
		  //mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);

	  }
	  Minecraft.getMinecraft().renderGlobal.loadRenderers();
	 }

	}
