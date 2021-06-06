package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.src.Mods;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Glide extends ModuleBase{

	public Glide()
	{
		super("Glide", "Allows you to glide through the air as if you had a parachute", KeyBindings.GLIDE, 0xFF00D8FF, Catagory.PLAYER);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			Mods.glide = true;
		}else{
			Mods.glide = false;
		}
	}
	
}
