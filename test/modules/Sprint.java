package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.entity.player.EntityPlayerMP;

public class Sprint extends ModuleBase{
	
	private boolean isMoving;
	private EntityPlayerMP ep;
	
	public Sprint() {
		super("Sprint", "Let's you sprint without double-tapping forward", KeyBindings.SPRINT, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void tick()
	{
		if(mc.thePlayer.isCollidedHorizontally){ return; }
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			this.isMoving = true;
		}else{
			this.isMoving = false;
		}
		
		if(this.isMoving)
		{
			mc.thePlayer.setSprinting(true);
		}
	}

	
	
}
