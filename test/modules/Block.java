package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Block extends ModuleBase{
	
	private boolean isMoving;
	private EntityPlayerMP ep;
	
	public Block() {
		super("Block", "Right clicks/blocks for you when active", KeyBindings.BLOCK, 0, Catagory.COMBAT);
		//ep = mc.thePlayer;
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
	            			mc.gameSettings.keyBindUseItem.pressed = !mc.gameSettings.keyBindUseItem.pressed;
	            			//mc.playerController.attackEntity(mc.thePlayer, var10);
	            			//mc.thePlayer.swingItem();
	            		}
	            	}
	            	
	            	
	            }
		}
	}

	
	
}
