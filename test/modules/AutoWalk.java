package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class AutoWalk extends ModuleBase{

	public AutoWalk() {
		super("Auto Walk", "Simulates you holding the forward button on your keyboard.", KeyBindings.AUTOWALK, 0, Catagory.PLAYER);
	}

	public void toggleEvent()
	{
		if(!this.toggled())
		{
			mc.gameSettings.keyBindForward.pressed = false;
		}
	}
	
	
	public void tick()
	{
		if(!mc.gameSettings.keyBindForward.pressed)
		{
			mc.gameSettings.keyBindForward.pressed = true;
		}
	}
}
