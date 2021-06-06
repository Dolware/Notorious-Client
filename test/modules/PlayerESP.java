package test.modules;

import java.awt.Color;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.utils.RenderUtil;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.tileentity.TileEntityChest;

public class PlayerESP extends ModuleBase{
	
	public PlayerESP() {
		super("Player ESP", "Draws a box around players", KeyBindings.PLAYERESP, 0xFF00D8FF, Catagory.RENDER);
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
	
	 public void onRender()
	  {
	    for (Iterator localIterator = this.mc.theWorld.loadedEntityList.iterator(); localIterator.hasNext();)
	    {
	      Object theObject = localIterator.next();
	      if ((theObject instanceof EntityOtherPlayerMP))
	      {
	        EntityOtherPlayerMP thePlayer = (EntityOtherPlayerMP)theObject;
	        double d = thePlayer.lastTickPosX + (thePlayer.posX - thePlayer.lastTickPosX) * this.mc.timer.renderPartialTicks;
	        double d1 = thePlayer.lastTickPosY + (thePlayer.posY - thePlayer.lastTickPosY) * this.mc.timer.renderPartialTicks;
	        double d2 = thePlayer.lastTickPosZ + (thePlayer.posZ - thePlayer.lastTickPosZ) * this.mc.timer.renderPartialTicks;
	        RenderUtil.drawPlayerESP(d - this.mc.getRenderManager().renderPosX, d1 - this.mc.getRenderManager().renderPosY, d2 - this.mc.getRenderManager().renderPosZ, thePlayer, thePlayer.height - 0.1D, thePlayer.width - 0.12D);
	        
	      }
}
	  }
}
