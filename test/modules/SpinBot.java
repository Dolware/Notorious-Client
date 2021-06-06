package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.gui.Console;
import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.src.Mods;
import net.gooby.ass.src.mods.Friends;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class SpinBot extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	private float originalPitch = 0F;
	private float originalYaw = 0F;
	
	public SpinBot() {
		super("Spinbot", "Sends packets to the server making you look at the ground and spin", KeyBindings.SPINBOT, 0xFF00D8FF, Catagory.COMBAT);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			this.originalYaw = mc.thePlayer.getRotationYawHead();
			this.originalPitch = mc.thePlayer.rotationPitch;
		}else{
			mc.thePlayer.rotationYaw = this.originalYaw;
			mc.thePlayer.rotationPitch = this.originalPitch;
		}
	}
	
	public void tick()
	{
		if(this.toggled())
		{
			/*mc.thePlayer.rotationYaw += Main.instance.spinSpeed;
			mc.thePlayer.rotationPitch = 100;*/
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw += Main.instance.spinSpeed, mc.thePlayer.rotationPitch = 100, mc.thePlayer.onGround));
		}
	}
	
	
}
