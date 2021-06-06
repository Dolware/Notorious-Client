package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.gooby.ass.src.Mods;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DerpDrunk extends ModuleBase{
	
	//private EntityPlayerMP ep;
	private static int clientBind;
	
	public DerpDrunk() {
		super("Drunk Mode", "Gives you a nausea effect and it replaces all of your chat messages", KeyBindings.DERPDRUNK, 0, Catagory.MISC);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, Integer.MAX_VALUE, 3));
			Mods.drunk = true;
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
		}else{
			this.mc.thePlayer.removePotionEffect(Potion.confusion.id);
			Mods.drunk = false;
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void tick()
	{
		
	}
	
	
}
