package net.gooby.ass.gui;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.gooby.ass.src.ClientMessages;
import net.gooby.ass.src.HandleConsole;
import net.gooby.ass.src.IRC;
import net.gooby.ass.src.Mods;
import net.gooby.ass.src.mods.Friends;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import test.modules.Main;

public class Console extends GuiScreen
{
	protected CustomTextField inputField;
	private String historyBuffer = "";
	protected Minecraft mc = Minecraft.getMinecraft();
	private final List sentMessages = Lists.newArrayList();
	public EntityPlayerSP ep = mc.thePlayer;
	public static boolean on = false;
	private final List field_146253_i = Lists.newArrayList();
	private int scrollPos;
	private boolean isScrolled;
	private int sentHistoryCursor = -1;
	public static boolean msgThread, ircOn;
	private static boolean checkPlugin = true;
	
	private IRC ircClass = new IRC();

	private String defaultInputFieldText = "";

	public Console() {}
	
	public void addToSentMessages(String message)
	{
		/*if (this.sentConsoleMessages.isEmpty() || !((String)this.sentConsoleMessages.get(this.sentConsoleMessages.size() - 1)).equals(message))
		{
			this.sentConsoleMessages.add(message);
		}*/
	}

	public Console(String defaultInput)
	{
		this.defaultInputFieldText = defaultInput;
	}

	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		this.sentHistoryCursor = this.sentMessages.size();
		this.inputField = new CustomTextField(0, this.fontRendererObj, 4, this.height - 12, this.width - 4, 12);
		this.inputField.setMaxStringLength(100);
		this.inputField.setEnableBackgroundDrawing(false);
		this.inputField.setFocused(true);
		this.inputField.setText(this.defaultInputFieldText);
		this.inputField.setCanLoseFocus(false);
	}

	public void toggleGUI()
	{
		on =! on;
	}

	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
		this.mc.ingameGUI.getChatGUI().resetScroll();
	}

	public void updateScreen()
	{
		this.inputField.updateCursorCounter();
	}

	public void sendPlayerMessage(String input)
	{
		ep.addChatComponentMessage(new ChatComponentText(new String(Mods.clientPrefix + input).replaceAll("&", "§")));
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == 1)
		{
			this.mc.displayGuiScreen((GuiScreen)null);
		}
		else if (keyCode != 28 && keyCode != 156)
		{
			if (keyCode == 200)
			{
				this.getSentHistory(-1);
			}
			else if (keyCode == 208)
			{
				this.getSentHistory(1);
			}
			else if (keyCode == 201)
			{
				this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
			}
			else if (keyCode == 209)
			{
				this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
			}
			else
			{
				this.inputField.textboxKeyTyped(typedChar, keyCode);
			}
		}
		else
		{
			String var3 = this.inputField.getText().trim();

			if (var3.length() > 0)
			{
				String[] varSplit = var3.split(" ");
				if(Arrays.asList(HandleConsole.validCommands).contains(varSplit[0]))
				{
					if(varSplit[0].toLowerCase().equals("help"))
					{
						sendPlayerMessage("&m+-------------------------------+");
						sendPlayerMessage("help | Displays this page");
						sendPlayerMessage("ch <command> | Displays help about the specified command");
						sendPlayerMessage("keys | Displays some key binds");
						sendPlayerMessage("commands | Displays console commands");
						sendPlayerMessage("&m+-------------------------------+");
					}
					
					if(varSplit[0].toLowerCase().equals("ch"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									if(Arrays.asList(HandleConsole.validCommands).contains(varSplit[1].toString()))
									{
										if(varSplit[1].toLowerCase().equals("help"))
										{
											sendPlayerMessage("help | Displays the help info for the client");
										}
										if(varSplit[1].toLowerCase().equals("ch"))
										{
											sendPlayerMessage("ch | Displays info/usage for the specified command");
										}
										if(varSplit[1].toLowerCase().equals("keys"))
										{
											sendPlayerMessage("keys | Displays some key binds for the client");
										}
										if(varSplit[1].toLowerCase().equals("commands"))
										{
											sendPlayerMessage("commands | Displays a list of client commands");
										}
										if(varSplit[1].toLowerCase().equals("dox"))
										{
											sendPlayerMessage("dox <target> | Opens a Google page searching the target (Use %20 for spaces) [&eVIP Only&f]");
										}
										if(varSplit[1].toLowerCase().equals("resolve"))
										{
											sendPlayerMessage("resolve <target> | Resolves the specified target Skype [&eVIP Only&f]");
										}
										if(varSplit[1].toLowerCase().equals("nh"))
										{
											sendPlayerMessage("nh <target> | Tries to find specified target's name history [&eVIP Only&f]");
										}
										if(varSplit[1].toLowerCase().equals("clip"))
										{
											sendPlayerMessage("clip <pos> <amount> | Multi-clip; basically VClip just with access to the X and Z");
										}
										if(varSplit[1].toLowerCase().equals("clientmsg"))
										{
											sendPlayerMessage("clientmsg <true/false> | Enables or Disables the thread for client messages");
										}
									}else{
										sendPlayerMessage("You did not specify a valid command!");
									}
								}else{
									sendPlayerMessage("You specified too many commands!");
								}
							}else{
								sendPlayerMessage("You did not specify a command!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("mimic"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									//sendPlayerMessage(varSplit[1]);
									for(int i=0; i < mc.theWorld.playerEntities.size(); i++)
									{
										Entity plEntity = (Entity) mc.theWorld.playerEntities.get(i);
										Main.instance.loadedPlayers.add(plEntity.getName());
										//writer.println(plEntity.getName()+" | "+plEntity.getPosition()+" | "+plEntity.getInventory()+"\n");
									}
									
									if(Main.instance.loadedPlayers.contains(varSplit[1]))
									{
										Main.instance.targetPlayer = varSplit[1];
										sendPlayerMessage("Set the target player to '"+varSplit[1]+"'!");
									}else{
										sendPlayerMessage("You did not specify a valid player!");
									}
								}else{
									sendPlayerMessage("You specified too many commands!");
								}
							}else{
								sendPlayerMessage("You did not specify a command!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("spinbot"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									int providedArg = Integer.parseInt(varSplit[1]);
									if(providedArg >= 1)
									{
										if(providedArg <= 40)
										{
											Main.instance.spinSpeed = providedArg;
											sendPlayerMessage("Set 'Spinbot' speed to: "+Main.instance.spinSpeed);
										}else{
											sendPlayerMessage("You did not specify a valid speed(1-40)!");
										}
									}else{
										sendPlayerMessage("You did not specify a valid speed(1-40)!");
									}
								}else{
									sendPlayerMessage("You specified too many arguments!");
								}
							}else{
								sendPlayerMessage("You did not specify an argument!");
							}
					}


					if(varSplit[0].toLowerCase().equals("keys"))
					{
						sendPlayerMessage("&m+-------------------------------+");
						sendPlayerMessage("F | Fly");
						sendPlayerMessage("C | Brightness");
						sendPlayerMessage("K | Killaura");
						sendPlayerMessage("L | Tracers");
						sendPlayerMessage("N | No Fall");
						sendPlayerMessage("&m+-------------------------------+");
						//mc.thePlayer.nam
					}
					
					if(varSplit[0].equalsIgnoreCase("loadplugin"))
					{
						if(varSplit.length > 1)
						{
							if(varSplit.length < 3)
							{
								try{
									if(this.checkPlugin){
										URL url = new URL(
												"http://goobychan.zz.mu/Notorious/verify.php?plugin=" + varSplit[1]);
										BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
										String firstLine = in.readLine();
										if(!firstLine.contains(varSplit[1]))
										{
											String output = in.readLine();
											in.close();
											//this.sendPlayerMessage(output);
											if (output.contains("Fatal error")) {
												this.sendPlayerMessage("WARNING! This plugin is not verified, normally this could lead to security issues. If you trust this plugin, please type the command again!");
												this.checkPlugin = false;
											}
										}else{
											try{
												Main.instance.apimanager.reloadModules(varSplit[1]);
											} catch (ClassNotFoundException e) {
												this.sendPlayerMessage("Could not find specified plugin!");
												e.printStackTrace();
											} catch (NoSuchMethodException e) {
												this.sendPlayerMessage("Plugin did not contain a valid method!");
												e.printStackTrace();
											} catch (SecurityException e) {
												this.sendPlayerMessage("Plugin contained a security violation!");
												e.printStackTrace();
											} catch (InstantiationException e) {
												this.sendPlayerMessage("Could not instantiate the plugin!");
												e.printStackTrace();
											} catch (IllegalAccessException e) {
												this.sendPlayerMessage("Plugin contained an illegal access violation!");
												e.printStackTrace();
											} catch (IllegalArgumentException e) {
												this.sendPlayerMessage("Plugin contained an illegal argument violation");
												e.printStackTrace();
											} catch (InvocationTargetException e) {
												this.sendPlayerMessage("Plugin contained a violation that did not allow an invoking of a statement!");
												e.printStackTrace();
											}
										}
									}else{
										if(this.checkPlugin = false)
										{
											try{
												Main.instance.apimanager.reloadModules(varSplit[1]);
											} catch (ClassNotFoundException e) {
												this.sendPlayerMessage("Could not find specified plugin!");
												e.printStackTrace();
											} catch (NoSuchMethodException e) {
												this.sendPlayerMessage("Plugin did not contain a valid method!");
												e.printStackTrace();
											} catch (SecurityException e) {
												this.sendPlayerMessage("Plugin contained a security violation!");
												e.printStackTrace();
											} catch (InstantiationException e) {
												this.sendPlayerMessage("Could not instantiate the plugin!");
												e.printStackTrace();
											} catch (IllegalAccessException e) {
												this.sendPlayerMessage("Plugin contained an illegal access violation!");
												e.printStackTrace();
											} catch (IllegalArgumentException e) {
												this.sendPlayerMessage("Plugin contained an illegal argument violation");
												e.printStackTrace();
											} catch (InvocationTargetException e) {
												this.sendPlayerMessage("Plugin contained a violation that did not allow an invoking of a statement!");
												e.printStackTrace();
											}
											this.checkPlugin = true;
										}
									}
								}catch (Exception e){
									e.printStackTrace();
								}
							}
						}
					}
					
					/*if(varSplit[0].equalsIgnoreCase("loadplugins")){
						try {
							
							
							
							Main.instance.apimanager.reloadModules();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/
					
				//if(Mods.isVIP){
					if(varSplit[0].toLowerCase().equals("dox"))
					{
						if(Mods.isVIP)
						{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
	                                try
	                                {
	                                	Desktop.getDesktop().browse(new URL("https://www.google.com/?gws_rd=ssl#q=" + varSplit[1].toString()).toURI());
	                                    sendPlayerMessage("Opened Google page for search on '" + varSplit[1].toString() + "'");
	                                }
	                                catch (Exception e)
	                                {
	                                    e.printStackTrace();
	                                }
								}else{
									sendPlayerMessage("You specified too many targets!");
								}
							}else{
								sendPlayerMessage("You did not specify a target!");
							}
						}else{
							sendPlayerMessage("You are not VIP. Go talk to Dolan for VIP access!");
						}
					}
					
					if(varSplit[0].toLowerCase().equals("resolve"))
					{
						if(Mods.isVIP)
						{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									try
									{
										URL url = new URL("http://api.abrasivecraft.com/?tool=skyperesolver&username=" + varSplit[1]);
										BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
										String output = in.readLine();
										in.close();
										sendPlayerMessage("IP for " + varSplit[1].toString() + "'s skype: " + output);
									}
									catch (Exception e)
									{
										sendPlayerMessage("API down, please try some other time!");
										e.printStackTrace();
									}
								}
								else
								{
									sendPlayerMessage("You specified too many targets!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify a target!");
							}
						}else{
							sendPlayerMessage("You are not VIP. Go talk to Dolan for VIP access!");
						}
					}
					
					if(varSplit[0].toLowerCase().equals("clientmsg"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									if(varSplit[1].equalsIgnoreCase("true"))
									{
										(new ClientMessages()).start();
										sendPlayerMessage("Started thread for Client Messages");
									}
									
									if(varSplit[1].equalsIgnoreCase("false"))
									{
										(new ClientMessages()).stop();
										sendPlayerMessage("Stopped thread for Client Messages");
									}
								}
								else
								{
									sendPlayerMessage("You specified too many arguments!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify an argument!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("name"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									mc.getSession().username = varSplit[1];
								}
								else
								{
									sendPlayerMessage("You specified too many arguments!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify an argument!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("clip"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 4)
								{
									if(Arrays.asList(Mods.validPos).contains(varSplit[1]))
									{
										if(varSplit[1].equalsIgnoreCase("x"))
										{
											try{
												ep.setPosition(ep.posX + Integer.parseInt(varSplit[2]), ep.posY, ep.posZ);
												sendPlayerMessage("Added '"+varSplit[2]+"' integers to the player X");
											}catch (Exception e){
												sendPlayerMessage("Could not change player X");
												e.printStackTrace();
											}
										}
										
										if(varSplit[1].equalsIgnoreCase("y"))
										{
											try{
												ep.setPosition(ep.posX, ep.posY + Integer.parseInt(varSplit[2]), ep.posZ);
												sendPlayerMessage("Added '"+varSplit[2]+"' integers to the player Y");
											}catch (Exception e){
												sendPlayerMessage("Could not change player Y");
												e.printStackTrace();
											}
										}
										
										if(varSplit[1].equalsIgnoreCase("z"))
										{
											try{
												ep.setPosition(ep.posX, ep.posY, ep.posZ + Integer.parseInt(varSplit[2]));
												sendPlayerMessage("Added '"+varSplit[2]+"' integers to the player Z");
											}catch (Exception e){
												sendPlayerMessage("Could not change player Z");
												e.printStackTrace();
											}
										}
									}
								}
								else
								{
									sendPlayerMessage("You specified too many arguments!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify an argument!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("nh"))
					{
						if(Mods.isVIP)
						{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 3)
								{
									try
									{
										URL nametoUUID = new URL("https://api.mojang.com/users/profiles/minecraft/" + varSplit[1].toString());
										BufferedReader in1 = new BufferedReader(new InputStreamReader(nametoUUID.openStream()));
										String output1 = in1.readLine();
										in1.close();
										String[] formatted1 = output1.split("\",\"");
										String formattedString = formatted1[0].substring(7, formatted1[0].length());
										System.out.println(formattedString);

										URL UUIDtonames = new URL("https://api.mojang.com/user/profiles/" + formattedString + "/names");
										BufferedReader in2 = new BufferedReader(new InputStreamReader(UUIDtonames.openStream()));
										String output2 = in2.readLine();
										in2.close();

										System.out.println(output2);

										String formatted2 = output2.substring(1, output2.length() - 2);
										sendPlayerMessage("Name history for '" + varSplit[1].toString() + "'");
										String[] st = formatted2.split(",");
										for(int i = 0; i < st.length; i++)
										{
											if(st[i].startsWith("{\"name\":\""))
											{
												sendPlayerMessage(new String(" - " + st[i].substring(9, st[i].length() - 1)).replaceAll("\"", ""));
											}
										}

									}
									catch (Exception e)
									{
										sendPlayerMessage("Could not get name history!");
										e.printStackTrace();
									}
								}
								else
								{
									sendPlayerMessage("You specified too many targets!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify a target!");
							}
						}else{
							sendPlayerMessage("You are not VIP. Go talk to Dolan for VIP access!");
						}
					}
				//}else{
				//	sendPlayerMessage("You are not VIP. Go talk to Dolan for VIP access!");
				//}

					/*if(varSplit[0].toLowerCase().equals("sneak"))
					{
						if(Mods.sneak)
						{
							Mods.sneak = false;
							sendPlayerMessage("Disabled 'sneak'!");
						}
						else
						{
							Mods.sneak = true;
							sendPlayerMessage("Enabled 'sneak'!");
						}
					}*/
					
					/*if(varSplit[0].toLowerCase().equals("irc-send"))
					{
						if(varSplit.length > 1)
						{
							if(varSplit.length < 3)
							{
								if(varSplit[1].equals(""))
								{
									sendPlayerMessage("You did not specify a message!");
								}else{
									ircClass.sendMessage(varSplit[1]);
								}
							}
							else
							{
								sendPlayerMessage("You specified too many arguments!");
							}
						}
						else
						{
							sendPlayerMessage("You didn't specify a arguments!");
						}
					} */
					
					if(varSplit[0].toLowerCase().equals("friend"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 4)
								{
									if(Arrays.asList(Mods.validFriendArgs).contains(varSplit[1]))
									{
										if(varSplit[1].equalsIgnoreCase("add"))
										{
											Main.instance.fr.friendList.add(varSplit[2]);
											mc.renderGlobal.loadRenderers();
										}
										
										if(varSplit[1].equalsIgnoreCase("remove"))
										{
											Main.instance.fr.friendList.remove(varSplit[2]);
										}
									}else{
										sendPlayerMessage("You didn't specify a valid argument!");
									}
								}
								else
								{
									sendPlayerMessage("You specified too many arguments!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify an argument!");
							}
					}
					
					if(varSplit[0].toLowerCase().equals("enemy"))
					{
							if(varSplit.length > 1)
							{
								if(varSplit.length < 4)
								{
									if(Arrays.asList(Mods.validFriendArgs).contains(varSplit[1]))
									{
										if(varSplit[1].equalsIgnoreCase("add"))
										{
											Main.instance.fr.enemyList.add(varSplit[2]);
											mc.renderGlobal.loadRenderers();
										}
										
										if(varSplit[1].equalsIgnoreCase("remove"))
										{
											Main.instance.fr.enemyList.remove(varSplit[2]);
										}
									}else{
										sendPlayerMessage("You didn't specify a valid argument!");
									}
								}
								else
								{
									sendPlayerMessage("You specified too many arguments!");
								}
							}
							else
							{
								sendPlayerMessage("You didn't specify an argument!");
							}
					}
					
					/*if(varSplit[0].toLowerCase().equals("irc"))
					{
						if(varSplit.length > 1)
						{
							if(varSplit.length < 3)
							{
								if(varSplit[1].toLowerCase().equals("on"))
								{
									if(this.ircOn)
									{
										ircClass.sendPlayerMessage("You are already connected!");
									}else{
										//ircClass.irc("irc.freenode.net", mc.thePlayer.getName(), mc.thePlayer.getName(), "#dyarchy", 6667);
										(new Thread(new IRC())).start();
										//ircClass.connect();
										this.ircOn = true;
									}
								}
								if(varSplit[1].toLowerCase().equals("off"))
								{
									if(this.ircOn)
									{
										(new Thread(new IRC())).stop();
										ircClass.sendPlayerMessage("You have disconnected!");
									}else{
										ircClass.sendPlayerMessage("You are not currently connected!");
									}
								}
							}
							else
							{
								sendPlayerMessage("You specified too many arguments!");
							}
						}
						else
						{
							sendPlayerMessage("You didn't specify a arguments!");
						}
					}*/
					
					if(varSplit[0].toLowerCase().equals("clear"))
					{
						for(int i = 0; i < 105; i++)
						{
							ep.addChatComponentMessage(new ChatComponentText(new String("")));
						}
						sendPlayerMessage("Chat cleared!");
					}
					
				/*	if(varSplit[0].toLowerCase().equals("sprint"))
					{
						if(Mods.sprint)
						{
							Mods.sprint = false;
							sendPlayerMessage("Disabled 'sprint'!");
						}
						else
						{
							Mods.sprint = true;
							sendPlayerMessage("Enabled 'sprint'");
						}
					}
					
					if(varSplit[0].toLowerCase().equals("fly"))
					{
						if(Mods.fly)
						{
							Mods.fly = false;
							sendPlayerMessage("Disabled 'fly'!");
						}
						else
						{
							Mods.fly = true;
							sendPlayerMessage("Enabled 'fly'");
						}
					}*/
					
					if(varSplit[0].toLowerCase().equals("debug"))
					{
						//System.out.println(mc.thePlayer.getName());
						//mc.thePlayer.forceSpawn = true;
						//(new ClientMessages()).start();
						//Mods.insta = !Mods.insta;
						/*sendPlayerMessage("Displaying loaded plugins!");
						sendPlayerMessage("&m+-------------------------------+");
						for(String plugin : ApiInfo.instance.all_plugins)
						{
							sendPlayerMessage(plugin);
						}
						sendPlayerMessage("&m+-------------------------------+");*/
						//sendPlayerMessage(ApiInfo.instance.);
						//String header, String text, int x, int y, int innerX, int innerY
					}
					
					if(varSplit[0].toLowerCase().equals("findall"))
					{
						if(Mods.isVIP)
						{
							try{
								PrintWriter writer = new PrintWriter(System.getenv("APPDATA") + "/.minecraft/Notorious/entities.txt", "UTF-8");
								for(int i=0; i < mc.theWorld.playerEntities.size(); i++)
								{
									Entity plEntity = (Entity) mc.theWorld.playerEntities.get(i);
									writer.println(plEntity.getName()+" | "+plEntity.getPosition()+" | "+plEntity.getInventory()+"\n");
								}
								writer.close();
								sendPlayerMessage("Dumped all player positions to "+System.getenv("APPDATA") + "/.minecraft/Notorious/entities.txt");
							}catch (Exception e){
								sendPlayerMessage("Could not dump entity positions!");
								e.printStackTrace();
							}
						}else{
							sendPlayerMessage("You are not VIP. Go talk to Dolan for VIP access!");
						}
					}
					
					if(varSplit[0].toLowerCase().equals("commands"))
					{
						sendPlayerMessage("help, ch, keys, commands, dox(&eVIP&f), sneak, sprint, nowater, resolve(&eVIP&f), nh(&eVIP&f), fly, clip, clientmsg, findall(&eVIP&f)");
					}
				}
				else
				{
					sendPlayerMessage("Invalid command specified!");
				}
			}
			else
			{
				sendPlayerMessage("You did not type in a command!");
			}
			this.sentMessages.add(var3);
			this.sentMessages.add(var3.toString());
			//addToSentMessages(var3);
			this.mc.displayGuiScreen((GuiScreen)null);
			toggleGUI();
		}
	}

    public boolean getChatOpen()
    {
        return this.mc.currentScreen instanceof Console;
    }
	
	public int getChatHeight()
	{
		return calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
	}

    public static int calculateChatboxHeight(float p_146243_0_)
    {
        short var1 = 180;
        byte var2 = 20;
        return MathHelper.floor_float(p_146243_0_ * (float)(var1 - var2) + (float)var2);
    }
	
	public int getLineCount()
	{
		return this.getChatHeight() / 9;
	}

	public void scroll(int p_146229_1_)
	{
		this.scrollPos += p_146229_1_;
		int var2 = this.field_146253_i.size();

		if (this.scrollPos > var2 - this.getLineCount())
		{
			this.scrollPos = var2 - this.getLineCount();
		}

		if (this.scrollPos <= 0)
		{
			this.scrollPos = 0;
			this.isScrolled = false;
		}
	}

    public void getSentHistory(int p_146402_1_)
    {
        int var2 = this.sentHistoryCursor + p_146402_1_;
        int var3 = this.sentMessages.size();
        var2 = MathHelper.clamp_int(var2, 0, var3);

        if (var2 != this.sentHistoryCursor)
        {
            if (var2 == var3)
            {
                this.sentHistoryCursor = var3;
                this.inputField.setText(this.historyBuffer);
            }
            else
            {
                if (this.sentHistoryCursor == var3)
                {
                    this.historyBuffer = this.inputField.getText();
                }

                this.inputField.setText((String)this.sentMessages.get(var2));
                this.sentHistoryCursor = var2;
            }
        }
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawRect(2, this.height - 15, this.width - 2, this.height - 14, Integer.MIN_VALUE);
		drawRect(2, this.height - 15, 3, this.height - 2, 0xFF2A0000);
		drawRect(2, this.height - 15, this.width - 2, this.height - 2, 0x80000000);
		drawRect(2, this.height - 3, this.width - 2, this.height - 2, 0xFF2A0000);
		drawRect(this.width - 3, this.height - 15, this.width - 2, this.height -2, 0xFF2A0000);
		this.inputField.drawTextBox();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public boolean doesGuiPauseGame()
	{
		return false;
	}
}