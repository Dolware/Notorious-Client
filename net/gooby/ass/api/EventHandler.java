package net.gooby.ass.api;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EventHandler {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public EventHandler(){}
	
	/** Setters **/
	public void sendPlayerMessage(String message)
	{
		mc.thePlayer.sendPlayerMessage(EventMain.clientPrefix+message.replaceAll("&", "§"));
	}
	
	public void makePlayerSneak(EntityPlayer entity)
	{
		if(!mc.thePlayer.isSneaking())
		{
			mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(entity, C0BPacketEntityAction.Action.START_SNEAKING));
		}else{
			mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(entity, C0BPacketEntityAction.Action.STOP_SNEAKING));
		}
	}
	
	public void makePlayerAttack(EntityPlayer entity)
	{
		mc.playerController.attackEntity(mc.thePlayer, entity);
	}
	
	public void makePlayerSwing(EntityPlayer entity)
	{
		entity.swingItem();
	}
	
	public void makePlayerJump(EntityPlayer entity)
	{
		entity.jump();
	}
	
	public void sendChatMessage(String message)
	{
		mc.thePlayer.sendChatMessage(message);
	}
	
	public void makePlayerFly(EntityPlayer entity, Boolean arg)
	{
		if(arg){
			entity.jump();
			entity.capabilities.isFlying = true;
		}else{
			entity.capabilities.isFlying = false;
		}
	}
	
	public void setPlayerSpeed(Float speed)
	{
		mc.thePlayer.capabilities.setPlayerWalkSpeed(speed);
	}
	
	public void setFlySpeed(Float speed)
	{
		mc.thePlayer.capabilities.setFlySpeed(speed);
	}
	
	
	
	
	/** Getters **/
	public Float getPlayerSpeed()
	{
		return mc.thePlayer.capabilities.getWalkSpeed();
	}
	
	public Float getFlySpeed()
	{
		return mc.thePlayer.capabilities.getFlySpeed();
	}
	

}
