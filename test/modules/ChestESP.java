package test.modules;

import java.awt.Color;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class ChestESP extends ModuleBase{
	
	public ChestESP() {
		super("Chest ESP", "Draws a box around chests", KeyBindings.CHESTESP, 0xFF00D8FF, Catagory.RENDER);
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
	
	public void drawESP(TileEntityChest chest, double x, double y, double z, float f)
	  {
	    if ((chest.numPlayersUsing != 0) || (chest.ticksSinceSync != 0) || (chest.cachedChestType != 0)) {
	      RenderUtil.drawChestESP(x - this.mc.getRenderManager().renderPosX, y - this.mc.getRenderManager().renderPosY, z - this.mc.getRenderManager().renderPosZ, chest.numPlayersUsing > 0 ? 0.27D : Color.BLUE.getRed(), chest.numPlayersUsing > 0 ? 0.0D : Color.BLUE.getGreen(), chest.numPlayersUsing > 0 ? 0.0D : Color.BLUE.getRed());
	    }
	  }
	
	public void onRender()
	  {
	    for (Iterator localIterator = this.mc.theWorld.loadedTileEntityList.iterator(); localIterator.hasNext();)
	    {
	      Object o = localIterator.next();
	      if ((o instanceof TileEntityChest))
	      {
	        TileEntityChest chest = (TileEntityChest)o;
	        
	        Minecraft.getMinecraft().gameSettings.viewBobbing = false;
	        drawESP(chest, chest.getPos().getX(), chest.getPos().getY(), chest.getPos().getZ(), 1.0F);
	      }
	    }
	  }
}
