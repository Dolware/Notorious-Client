package test.modules;

import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class DerpHump extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private boolean isHumping;
	
	public DerpHump() {
		super("Hump", "Ever love somebody? How about showing it!", KeyBindings.DERPHUMP, 0, Catagory.MISC);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			this.isHumping = true;
			
		}else{
			this.isHumping = false;
		}
	}
	
	public void tick()
	{
		//mc.gameSettings.keyBindJump.pressed = !mc.gameSettings.keyBindJump.pressed;
		if(this.isHumping)
		{
			this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
		}else{
			this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
		}
		this.isHumping = !this.isHumping;
	}
	
	
}
