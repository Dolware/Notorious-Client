package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.gui.Console;
import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.Mods;
import net.gooby.ass.src.mods.Friends;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Knockback extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	private float originalPitch = 0F;
	private float originalYaw = 0F;
	
	public Knockback() {
		super("No Knockback", "Allows you to not get knocked back during PvP", Keyboard.KEY_UNLABELED, 0xFF00D8FF, Catagory.COMBAT);
	}
	
	public void toggleEvent()
	{
		
	}
	
	public void tick()
	{
		if(this.toggled())
		{
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.getPosition().getX(), mc.thePlayer.getPosition().getY(), mc.thePlayer.getPosition().getZ(), mc.thePlayer.onGround));
		}
	}
	
	
}
