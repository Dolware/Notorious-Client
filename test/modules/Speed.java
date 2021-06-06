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

public class Speed extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private boolean checkSpeed = true;
	
	public Speed() {
		super("Speed", "Changes your walk speed to be a tad bit faster than normal", Keyboard.KEY_UNLABELED, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			mc.thePlayer.capabilities.setPlayerWalkSpeed(0.5F);
		}else{
			mc.thePlayer.capabilities.setPlayerWalkSpeed(0.1F);
		}
	}
	
	public void tick()
	{
		if(this.checkSpeed){
		System.out.println(mc.thePlayer.capabilities.getWalkSpeed());
		}
	}
	
}
