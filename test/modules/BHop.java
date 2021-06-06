package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;

public class BHop extends ModuleBase{
	
	private boolean isMoving;
	private EntityPlayerMP ep;
	
	public BHop() {
		super("B-Hop", "Causes you to hop around like a rabbit when on the ground and sprinting", KeyBindings.BHOP, 0xFF00D8FF, Catagory.COMBAT);
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
		if(this.toggled())
		{
			if(mc.thePlayer.isSprinting() && mc.thePlayer.onGround)
			{
				mc.thePlayer.jump();
			}
		}
	}

	
	
}
