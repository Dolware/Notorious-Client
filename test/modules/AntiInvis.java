package test.modules;

import java.util.Iterator;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.utils.RenderUtil;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class AntiInvis extends ModuleBase{
	
	public AntiInvis() {
		super("Anti-Invis", "Completely Nullifies if someone is invisible and draws them anyway", KeyBindings.ANTIINVIS, 0xFF00D8FF, Catagory.RENDER);
	}
	
	 public void onRender()
	  {
	    for (Iterator localIterator = this.mc.theWorld.loadedEntityList.iterator(); localIterator.hasNext();)
	    {
	      Object theObject = localIterator.next();
	      if ((theObject instanceof EntityOtherPlayerMP))
	      {
	        EntityOtherPlayerMP thePlayer = (EntityOtherPlayerMP)theObject;
	        
	        if(thePlayer.isInvisible() && this.toggled())
	        {
	        	thePlayer.setCustomNameTag("§b[INVIS]"+thePlayer.getName()+"§r");
	        	thePlayer.setAlwaysRenderNameTag(true);
	        	thePlayer.setInvisible(false);
	        }else if(!(this.toggled()) && thePlayer.isInvisible())
	        {
	        	thePlayer.setAlwaysRenderNameTag(false);
	        	thePlayer.setInvisible(true);
	        }else{
	        	thePlayer.setAlwaysRenderNameTag(false);
	        	thePlayer.setInvisible(false);
	        }
	      }
}
	  }

}
