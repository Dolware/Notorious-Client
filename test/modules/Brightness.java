package test.modules;

import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import test.modules.ModuleBase;
import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Brightness extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public Brightness() {
		super("Brightness", "Uses nightvision potion packet to let you see in the dark", KeyBindings.BRIGHTNESS, 0xFF00D8FF, Catagory.RENDER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 3));
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			
		}else{
			this.mc.thePlayer.removePotionEffect(Potion.nightVision.id);
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void onUpdate()
	{
		
	}
	
	
}
