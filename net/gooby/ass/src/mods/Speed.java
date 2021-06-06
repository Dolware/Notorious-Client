package net.gooby.ass.src.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Speed {
	
	public NetHandlerPlayServer playerNetServerHandler;
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public void EnableSpeed()
	{
		this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(mc.thePlayer.getEntityId(), new PotionEffect(Potion.moveSpeed.id, Integer.MAX_VALUE, 3)));
	}

}
