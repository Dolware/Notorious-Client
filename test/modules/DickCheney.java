package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.src.Mods;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DickCheney extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public DickCheney() {
		super("Dick Cheney", "Now you get to be a Walmart greeter!", KeyBindings.DICKCHENEY, 0, Catagory.MISC);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			Mods.dickCheney = true;
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
		}else{
			Mods.dickCheney = false;
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void tick()
	{
		
	}
	
	
}
