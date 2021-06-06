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
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Mimic extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public Mimic() {
		super("Mimic", "Makes your mimic the specified player", KeyBindings.MIMIC, 0xFF00D8FF, Catagory.MISC);
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
		for(int i =0; i < Minecraft.getMinecraft().theWorld.loadedEntityList.size(); i ++){
			 Entity var10 = (Entity)Minecraft.getMinecraft().theWorld.loadedEntityList.get(i);
	            if(var10 instanceof EntityPlayer){
	            	//Friend checking and check if the entity == you.
	            	if(var10.getName() != mc.thePlayer.getName() && !Main.instance.fr.friendList.contains(var10.getName())){
	            		if(var10.getName().equals(Main.instance.targetPlayer)){
	            		if(mc.thePlayer.getDistanceToEntity(var10) < 6){
	            			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(var10.rotationYaw, var10.rotationPitch, mc.thePlayer.onGround));
	            			/*Main.instance.targetX = var10.getPosition().getX();
	            			Main.instance.targetZ = var10.getPosition().getZ();*/
	            			
	            			if(!var10.onGround)
	            			{
	            				if(!mc.thePlayer.onGround)
	            				{
	            					//Do Nothing
	            				}else{
	            					mc.thePlayer.jump();
	            				}
	            			}
	            			if(!var10.isSneaking())
	            			{
	            				this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
	            			}else{
	            				this.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
	            			}
	            			
	            			if(((EntityPlayer) var10).swingProgress > 0)
	            			{
	            				this.mc.thePlayer.swingItem();
	            			}
	            		}
	            		}
	            	}
	            }
		}
	}
	
	
}
