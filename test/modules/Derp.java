package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Derp extends ModuleBase{
	
	//private EntityPlayerMP ep;
	
	public Derp() {
		super("Autism", "Simulator of Jordan Lee Tetro", KeyBindings.DERP, 0, Catagory.MISC);
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
		if(this.toggled())
		{
			this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(this.mc.thePlayer.rotationYaw, 180.0F, this.mc.thePlayer.onGround));
		}
	}
	
	
}
