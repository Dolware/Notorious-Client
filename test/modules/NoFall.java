package test.modules;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.src.KeyBindings;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends ModuleBase{

	public NoFall() {
		super("No Fall", "Allows you to take no fall damage when this is active", KeyBindings.NOFALL, 0, Catagory.PLAYER);
	}
	
	public void tick()
	{
		if (toggled()) {
		        if (this.mc.thePlayer.fallDistance > 2.0F)
		        {
		          mc.thePlayer.fallDistance = 0;
		        }
		      }
		        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		   }
	}
