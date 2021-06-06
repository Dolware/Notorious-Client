package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;

public class NoCheat extends ModuleBase{
	
	public NoCheat()
	{
		super("NoCheat Mode", "Toggles a few NoCheat bypasses", Keyboard.KEY_NONE, 0xFF00D8FF, Catagory.MISC);
	}

	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			Main.no_cheat = true;
			
		}else{
			Main.no_cheat = false;
		}
	}
}
