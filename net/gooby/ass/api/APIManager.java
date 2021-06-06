package net.gooby.ass.api;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import net.gooby.ass.gui.GuiClick;
import net.gooby.ass.src.Mods;
import net.minecraft.client.Minecraft;
import test.modules.Main;
import test.modules.ModuleBase;

public class APIManager {
	ArrayList<ModuleBase> modules;
	int pluginsLoaded;
	
	
	public void loadPlugin(String plugin) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		  File file = new File(System.getenv("APPDATA") + "/.minecraft/Notorious/plugins"); 
		   
		  
		  String[] names = file.list();

		  
		      if (new File(System.getenv("APPDATA") + "/.minecraft/Notorious/plugins/" + plugin).isDirectory())
		      {
		       URL url = file.toURI().toURL(); 
		    URL[] urls = new URL[]{url}; 

		          //load this folder into Class loader
		    ClassLoader cl = new URLClassLoader(urls); 

		          //load the Address class in 'c:\\other_classes\\'
		    File folder = new File(System.getenv("APPDATA") + "/.minecraft/Notorious/plugins/" + plugin);
		    File[] listOfFiles = folder.listFiles();

		    for (File file1 : listOfFiles) {
		        if (file1.isFile()) {

		         if(file1.getName().contains(".class")){
		          String namesr = file1.getName().replace(".class", "");
		          Class<? extends ModuleBase> cls = (Class<? extends ModuleBase>) cl.loadClass(plugin +"." + namesr);
		       //Minecraft.getMinecraft().thePlayer.sendChatMessage("Plugin classes: " + file1.getName());

		          Constructor<? extends ModuleBase> con = cls.getConstructor();
		          ModuleBase module = con.newInstance();
		          for(ModuleBase module1 : Main.instance.modules){
		           if(module1.getName() == module.getName()){
		            return;
		           }
		          }
		          Main.instance.modules.add(module);
		       Minecraft.getMinecraft().thePlayer.sendPlayerMessage("Plugin Loaded: " + plugin + "/" + namesr);
		       pluginsLoaded += 1;

		         }
		        }
		    }
		      
		  }
		    
		 }
	
	public void listModules(){
		for(ModuleBase module : Main.instance.modules){
			Minecraft.getMinecraft().thePlayer.sendChatMessage("Module: " + module.getName());
		}
	}
	
	
	public void reloadModules(String plugin) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//modules = new ArrayList<ModuleBase>();
		loadPlugin(plugin);
	
		//Main.instance.modules = modules;
		Main.instance.screen = new GuiClick();
		if(pluginsLoaded == 0){
			Minecraft.getMinecraft().thePlayer.sendPlayerMessage("No plugins to load!");
		}else{
			Minecraft.getMinecraft().thePlayer.sendPlayerMessage("Loaded " + pluginsLoaded + " plugins!");
			pluginsLoaded = 0;
		}
	}

}
