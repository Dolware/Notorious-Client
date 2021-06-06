package test.modules;

import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.api.EventHandler;
import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Sneak extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	private EventHandler events = new EventHandler();
	
	public Sneak() {
		super("Sneak", "Sends a packet to the server saying you're sneaking", KeyBindings.SNEAK, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			
			this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			
		}else{
			this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void onUpdate()
	{
		
	}
	
	
}
