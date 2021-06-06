package test.modules;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends ModuleBase{
	
	public Tracers() {
		super("Tracers", "Draws a line to every player within the vicinity", KeyBindings.TRACERS, 0xFF00D8FF, Catagory.RENDER);
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

	public void onRender() {
		  try {
		   Minecraft.getMinecraft().gameSettings.viewBobbing = false;
		   GL11.glPushMatrix();
		   GL11.glEnable(3042);
		   GL11.glEnable(2848);
		   GL11.glDisable(2929);
		   GL11.glDisable(2896);
		   GL11.glDisable(3553);
		   GL11.glBlendFunc(770, 771);
		   GL11.glEnable(3042);
		   GL11.glLineWidth(1.5F);
		   for (Iterator localIterator = Minecraft.getMinecraft().theWorld.loadedEntityList
		     .iterator(); localIterator.hasNext();) {
		    Object theObject = localIterator.next();
		    if ((theObject != Minecraft.getMinecraft().thePlayer)
		      && (theObject != null)) {
		     if ((theObject instanceof EntityPlayer)) {
		      EntityPlayer entity = (EntityPlayer) theObject;
		      double posX = entity.lastTickPosX
		        + (entity.posX - entity.lastTickPosX)
		        - this.mc.getRenderManager().renderPosX;
		      double posY = entity.lastTickPosY + 1.0D
		        + (entity.posY - entity.lastTickPosY)
		        - this.mc.getRenderManager().renderPosY;
		      double posZ = entity.lastTickPosZ
		        + (entity.posZ - entity.lastTickPosZ)
		        - this.mc.getRenderManager().renderPosZ;
		      Main cn = Main.instance;
			    //GL11.glColor4f(RED, GREEN, BLUE, ALPHA);
			    if(Main.instance.fr.friendList.contains(entity.getName()))
			    {
			    	GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			    }else if(Main.instance.fr.enemyList.contains(entity.getName()))
			    {
			    	GL11.glColor4f(1.0F,0.0F, 0.0F, 1.0F);
			    }else{
			    	GL11.glColor4f(0.5F, 1.0F, 1.0F, 1.0F);
			    }

		      GL11.glBegin(2);
		      GL11.glVertex3d(0.0D,
		        0.0D + this.mc.thePlayer.getEyeHeight(), 0.0D);
		      GL11.glVertex3d(posX, posY, posZ);
		      GL11.glEnd();
		     }
		    }
		   }
		   GL11.glDisable(3042);
		   GL11.glEnable(3553);
		   GL11.glEnable(2929);
		   GL11.glDisable(2848);
		   GL11.glDisable(3042);
		   GL11.glEnable(2896);

		   GL11.glPopMatrix();
		  } catch (Exception localException) {
		  }
		 }
	
}
