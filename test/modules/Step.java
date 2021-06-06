package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.minecraft.entity.Entity;

public class Step extends ModuleBase{
	
	public Step()
	{
		super("Step", "Allows you to step up one full block", Keyboard.KEY_NONE, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			mc.thePlayer.stepHeight = 1F;
			
		}else{
			mc.thePlayer.stepHeight = 0.5F;
		}
	}
	
	
	public void tick()
	{
		Entity var10 = (Entity)mc.thePlayer;
		
		if(mc.thePlayer.stepHeight != 1F)
		{
			mc.thePlayer.stepHeight = 1F;
		}
		
		if(Main.no_cheat)
		{
			if(mc.thePlayer.isCollidedHorizontally)
			{
				mc.playerController.attackEntity(mc.thePlayer, var10);
			}
		}
	}

}
