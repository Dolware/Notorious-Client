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

public class KillAura extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public KillAura() {
		super("KillAura", "Attacks the players around you unless they are your friend", KeyBindings.KILLAURA, 0xFF00D8FF, Catagory.COMBAT);
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
	            		
	            		if(mc.thePlayer.getDistanceToEntity(var10) < 6){
	            			if(!(var10.isInvisible()))
	            			{
		            			mc.playerController.attackEntity(mc.thePlayer, var10);
		            			mc.thePlayer.swingItem();
	            			}
	            		}
	            	}
	            	
	            	
	            }
		}
	}
	
	
}
