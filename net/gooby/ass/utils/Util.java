package net.gooby.ass.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Util {

	private static Minecraft mc = Minecraft.getMinecraft();
	
	 public static int genRandom(final int min, final int max)
	 {
		 return min + (int) (Math.random() * ((max - min) + 1));
	 }
	 
	  public static void damage()
	  {
	    for (int i = 0; i < 4; i++)
	    {
	      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.01D, mc.thePlayer.posZ, false));
	      mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	    }
	    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.1D, mc.thePlayer.posZ, false));
	  }
	  
	
}
