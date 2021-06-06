package test.modules;

import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Criticals extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public Criticals() {
		super("Criticals", "Jumps when you swing causing critical hits", KeyBindings.CRITICALS, 0, Catagory.COMBAT);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			
		}else{
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void tick()
	{
		if(mc.thePlayer.isSwingInProgress)
		{
			mc.gameSettings.keyBindJump.pressed = !mc.gameSettings.keyBindJump.pressed;
		}else{
			//Do Nothing
		}
	}
	
	
}
