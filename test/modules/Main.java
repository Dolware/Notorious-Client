package test.modules;

import java.util.ArrayList;

import net.gooby.ass.api.APIManager;
import net.gooby.ass.gui.GuiClick;
import net.gooby.ass.src.mods.Friends;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

public class Main {

	public ArrayList<ModuleBase> modules = new ArrayList<ModuleBase>();
	public ArrayList<String> loadedPlayers = new ArrayList<String>();
	public ArrayList<String> cookieTriggers = new ArrayList<String>();
	public ArrayList<String> cookieLogs = new ArrayList<String>();
	public static Main instance = new Main();
	public static boolean spiderOn, no_cheat;
	public GuiClick screen = new GuiClick();
	public APIManager apimanager = new APIManager();
	public static Friends fr = new Friends();
	public boolean fly_enabled;
	
	//Mimic variables
	public String targetPlayer = "";
	public double targetX = 0.0;
	public double targetZ = 0.0;
	
	public int spinSpeed = 0;
	
	public net.minecraft.block.Block[] blocks = {Blocks.diamond_ore, Blocks.diamond_block, Blocks.gold_ore, Blocks.gold_block, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.chest, Blocks.ender_chest, Blocks.mob_spawner};
	
	public Main(){
		modules.add(new Fly());
		modules.add(new Tracers());
		modules.add(new Sprint());
		modules.add(new Sneak());
		modules.add(new Derp());
		modules.add(new Brightness());
		modules.add(new NoWater());
		modules.add(new Block());
		modules.add(new KillAura());
		modules.add(new DerpHump());
		modules.add(new DerpDrunk());
		modules.add(new Criticals());
		modules.add(new DickCheney());
		modules.add(new FastMine());
		modules.add(new FastPlace());
		modules.add(new Xray());
		modules.add(new NoRain());
		modules.add(new DayTime());
		modules.add(new AutoWalk());
		modules.add(new NoFall());
		modules.add(new ChestESP());
		modules.add(new PlayerESP());
		modules.add(new Glide());
		modules.add(new Aimbot());
		modules.add(new Mimic());
		modules.add(new SpinBot());
		modules.add(new Knockback());
		modules.add(new BHop());
		modules.add(new Speed());
		modules.add(new AntiInvis());
		modules.add(new Test());
		
		modules.add(new Step());
		
		modules.add(new NoCheat());
		
		/*modules.add(new ArrayL());
		modules.add(new Radar());
		modules.add(new PlayerInfo());*/
		
		/*if(Minecraft.getMinecraft().session.getUsername().equals("crazyspartan5"))
		{*/
			//modules.add(new CookieBot());
		//}
	}
	
	
	
}
