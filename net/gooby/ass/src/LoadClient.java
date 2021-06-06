package net.gooby.ass.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.dto.RealmsServer.McoServerComparator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class LoadClient{
	public String clientVersion = "1.0";
	public static String playerName;
	public static boolean getBinds = true;
	public static Minecraft mc = Minecraft.getMinecraft();
	private static AudioInputStream ais;
	private static Clip clip;
	
	public static void MakeClientDir() {
		File cFile = new File(System.getenv("APPDATA") + "/.minecraft/Notorious");
		if (!cFile.exists()) {
			if (cFile.mkdir()) {
				// Do nothing
			} else {
				// Do nothing
			}
		}

		// FileUtils.copyURLToFile("http://trenchcoatmafia.net/imgay.mp3",
		// System.getenv("APPDATA") + "/.minecraft/Notorious/music.mp3");
	}
	
	public static void PlayClientMusic(String cmd) throws Exception {
		//URL url = new URL("http://goobychan.zz.mu/Notorious/rape.mp3");
		ais = AudioSystem.getAudioInputStream(new File(System.getenv("APPDATA") + "/.minecraft/Notorious/sound/rape.wav").getAbsoluteFile());
		clip = AudioSystem.getClip();

		if (cmd.equals("stop")) {
			clip.stop();
			clip.close();
			clip.flush();
		} else {
			clip.open(ais);
			clip.loop(-1);
			clip.start();
		}
	}
	
	public static void setBinds()
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(System.getenv("APPDATA") + "/.minecraft/Notorious/keybinds.txt"));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			 
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] splitter = line.split(":");
				switch(splitter[0])
				{
				case "Aimbot":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.AIMBOT = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.AIMBOT = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Autowalk":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.AUTOWALK = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.AUTOWALK = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Bhop":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.BHOP = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.BHOP = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Block":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.BLOCK = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.BLOCK = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Brightness":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.BRIGHTNESS = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.BRIGHTNESS = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Chestesp":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.CHESTESP = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.CHESTESP = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Criticals":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.CRITICALS = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.CRITICALS = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Daytime":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.DAYTIME = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.DAYTIME = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Derp":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.DERP = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.DERP = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Drunk":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.DERPDRUNK = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.DERPDRUNK = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Hump":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.DERPHUMP = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.DERPHUMP = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Dickcheney":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.DICKCHENEY = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.DICKCHENEY = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					};
					break;
				case "Fastmine":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.FASTMINE = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.FASTMINE = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Fastplace":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.FASTPLACE = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.FASTPLACE = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Fly":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.FLY = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.FLY = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Glide":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.GLIDE = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.GLIDE = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Killaura":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.KILLAURA = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.KILLAURA = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Mimic":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.MIMIC = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.MIMIC = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Nofall":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.NOFALL = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.NOFALL = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Norain":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.NORAIN = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.NORAIN = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Nowater":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.NOWATER = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.NOWATER = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Playeresp":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.PLAYERESP = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.PLAYERESP = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Sneak":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.SNEAK = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.SNEAK = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Spinbot":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.SPINBOT = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.SPINBOT = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Sprint":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.SPRINT = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.SPRINT = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Tracers":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.TRACERS = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.TRACERS = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				case "Xray":
					if(splitter[1].equalsIgnoreCase("NONE"))
					{
						KeyBindings.XRAY = Keyboard.KEY_UNLABELED;
					}else{
						KeyBindings.XRAY = Keyboard.getKeyIndex(splitter[1].trim().toUpperCase());
					}
					break;
				}
			}
		 
			br.close();
			System.out.println(KeyBindings.SNEAK);
			System.out.println(KeyBindings.FLY);
			System.out.println(KeyBindings.SPRINT);
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}

}
