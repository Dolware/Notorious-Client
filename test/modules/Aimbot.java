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

public class Aimbot extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public Aimbot() {
		super("Aimbot", "Makes your camera snap to the closest player", KeyBindings.AIMBOT, 0xFF00D8FF, Catagory.COMBAT);
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
	            		if(var10.getName().equals("GoobyAss")){
	            		if(mc.thePlayer.getDistanceToEntity(var10) < 6){
	            			mc.thePlayer.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, var10.getPosition().getX(), var10.getPosition().getY());
	            		}
	            		}
	            	}
	            }
		}
	}
	
	
}
