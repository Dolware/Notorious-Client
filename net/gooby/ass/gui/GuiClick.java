package net.gooby.ass.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.regex.Pattern;

import net.gooby.ass.src.Catagory;
//import net.gooby.ass.src.MediaManager;
//import net.gooby.ass.src.Theme;
import net.gooby.ass.utils.GuiUtils;
import net.gooby.ass.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import test.modules.Main;
import test.modules.ModuleBase;

public class GuiClick extends GuiScreen {
	
	 int mouseoldx, mouseoldy;
	 long time;
	//Icons//
	final Texture PLAYER = new Texture("textures/gui/user.png");
	final Texture RENDER = new Texture("textures/gui/render.png");
	final Texture WORLD = new Texture("textures/gui/world.png");
	final Texture COMBAT = new Texture("textures/gui/combat.png");
	final Texture MISC = new Texture("textures/gui/misc.png");
	public final Texture RADAR = new Texture("textures/gui/radar.png");
	public final Texture ARRAY = new Texture("textures/gui/array.png");
	final Texture SETTINGS = new Texture("textures/gui/settings.png");
	final Texture MEDIA = new Texture("textures/gui/music.png");

	//private MediaManager mm  = new MediaManager(System.getenv("APPDATA") + "/.minecraft/Notorious/Music/test.mp3");
	//Icons//
	//Header vars//
		//Coords//
		public int playerx = 3, playery = 3;
		public int worldx = 103, worldy = 3;
		public int renderx = 203, rendery = 3;
		public int combatx = 303, combaty = 3;
		public int miscx = 403, miscy = 3;
		public int radarx = 503, radary = 3;
		public int arrayx = 603, arrayy = 3;
		public int mediax = 603, mediay = 18;
		
		public int flyValue = 0;
		public int flyX = 10;
		public int flyY = 10;
		public boolean isFlyDragging;
		//Coords//
		
		//Booleans//
		public static boolean playerIsDragging, playerIsOpen, playerIsVisible;
		public static boolean worldIsDragging, worldIsOpen, worldIsVisible;
		public static boolean renderIsDragging, renderIsOpen, renderIsVisible;
		public static boolean combatIsDragging, combatIsOpen, combatIsVisible;
		public static boolean miscIsDragging, miscIsOpen, miscIsVisible;
		public static boolean radarIsDragging, radarIsOpen, radarIsVisible, radarIsPinned;
		public static boolean arrayIsDragging, arrayIsOpen, arrayIsVisible, arrayIsPinned;
		public static boolean mediaIsDragging, mediaIsOpen, mediaIsVisible, mediaIsPinned, mediaIsPaused;
		public static boolean settingsIsOpen;
		//Booleans//
	//Header Vars
	//Theme theme = Theme.NEON_BLUE;
	int themeColorBorder = 0xFFFFD700, themeColorInside1 = Color.DARK_GRAY.getRGB(), themeColorInside2 = Color.BLACK.getRGB();
	public GuiUtils g = new GuiUtils();
	public RenderItem ri = mc.getRenderItem();

	
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	
	public void drawHeader(Catagory type, Texture texture, String text, int x, int y, boolean pinnable, boolean pinned, boolean extended, boolean visible){
		
		
		if(visible == false){ return; }
		  this.drawBorderedGradientRect(2 + x, y, 98 + x, y + 15, 1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
		  this.drawString(mc.fontRendererObj, text, x + mc.fontRendererObj.getStringWidth("Player"), y + 3, Color.GRAY.getRGB());
		  this.drawBorderedRect(80 + x, y + 2, 96 + x, y + 13, 1, 0xFF191919, extended ? 0xFF666666 : Color.DARK_GRAY.getRGB());
		  texture.renderTexture(x + 5, y + 1, 12, 12, RenderUtil.HexToRGB("#FFFFFF"));

		  if(pinnable){
			  g.drawBorderedCircle(76 + x, y+6, 3, !pinned ? Color.RED.getRGB() : 0xFF009900, 2, 0xFF191919);

		  }
		  if(extended){
			  drawExtended(type, x, y);
		  }
		  /**if(extended == false){
			  this.drawBorderedRect(80 + x, y + 2, 96 + x, y + 13, 1, 0xFF191919, Color.red.getRGB());
			  this.drawBorderedRect(80 + x, y + 2, 85 + x, y + 13, 1, 0xFF191919, 0xFF333333);

		  }else{
			  this.drawBorderedRect(80 + x, y + 2, 96 + x, y + 13, 1, 0xFF191919, 0xFF009900);
			  this.drawBorderedRect(91 + x, y + 2, 96 + x, y + 13, 1, 0xFF191919, 0xFF333333);
		  }**/
		  //g.drawRoundedRect(x + playerx, playery, x + playerx + 100, playery, 2, Color.GRAY.getRGB());
	}
	
	public void drawExtended(Catagory type, int x, int y){
		int bottom = 18;
		if(type != Catagory.ARRAYLIST && type != Catagory.RADAR && type != Catagory.MEDIA){
			for(ModuleBase module : Main.instance.modules){
				
					if(module.getType() == type){
						bottom += 13;
					}
				
				
			}
		}else{
			if(type == Catagory.ARRAYLIST){
				for(ModuleBase module : Main.instance.modules){
					if(module.toggled()){
							bottom += 13;
						
					}
					
				}
			}
			if(type == Catagory.RADAR){
				for(int i = 0; i < mc.theWorld.playerEntities.size(); i ++){
					EntityPlayer pl = (EntityPlayer)mc.theWorld.playerEntities.get(i);
					if(pl.getName() != mc.thePlayer.getName()){
						bottom += 13;
					}
					
				}
			}
			if(type == Catagory.MEDIA){
				bottom += 50;
			}
		}
		if(bottom == 0){
			bottom = 50;
		}
		this.drawBorderedGradientRect(2 + x, 17 + y, 98 + x, 2 + y + bottom,1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
		
		if(type == Catagory.ARRAYLIST){
			int arx = x;
			int ary = 13;
			for(ModuleBase module : Main.instance.modules){
				if(module.toggled()){
					
					this.drawBorderedGradientRect(6 + x, 6 + y + ary, 94 + x, 18+y + ary,1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
					this.drawString(mc.fontRendererObj, module.getName(), x + 9,8 + y + ary, Color.gray.getRGB());
					ary += 13;
				}
				
				
			}
		}else if(type == Catagory.RADAR){
			int arx = x;
			int ary = 13;
			for(int i = 0; i < mc.theWorld.playerEntities.size(); i ++){
					EntityPlayer pl = (EntityPlayer)mc.theWorld.playerEntities.get(i);
					if(pl.getName() != mc.thePlayer.getName()){
						this.drawBorderedGradientRect(6 + x, 6 + y + ary, 94 + x, 18+y + ary,1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
						if(Main.fr.friendList.contains(pl.getName())){
							this.drawString(mc.fontRendererObj, pl.getName(), x + 9,8 + y + ary, Color.GREEN.getRGB());
						}else if(Main.fr.enemyList.contains(pl.getName())){
							this.drawString(mc.fontRendererObj, pl.getName(), x + 9,8 + y + ary, Color.RED.getRGB());
						}else{
							this.drawString(mc.fontRendererObj, pl.getName(), x + 9,8 + y + ary, Color.GRAY.getRGB());
						}
						ary += 13;
					}
					
				
				
				
			}
		}else if(type == Catagory.MEDIA){
			/**
			this.drawString(mc.fontRendererObj, "Current", x + 35,18 + y, Color.GRAY.getRGB());
			this.drawString(mc.fontRendererObj, mm.getFile().getName().split(Pattern.quote("."))[0], x + 40,26 + y, Color.WHITE.getRGB());
			this.drawString(mc.fontRendererObj, "Time", x + 40,35 + y, Color.GRAY.getRGB());
			this.drawString(mc.fontRendererObj, mm.getCurrentTime() + " - " + mm.getDurationTime(), x + 28,43 + y, Color.WHITE.getRGB());
			
			//PLAY PAUSE STOP BUTTONS//
			
			
			this.drawBorderedRect(x + 30, y + 57, x + 70, y + 68, 1, Color.WHITE.getRGB(), Color.DARK_GRAY.getRGB());
			this.drawString(mc.fontRendererObj, !mediaIsPaused ? "Play" : "Pause",!mediaIsPaused ? 42 + x : 40 + x, y + 58, Color.GRAY.getRGB());
			
			//BACK FORWARD BUTTONS//
			this.drawBorderedRect(x + 4, y + 57, x + 25, y + 68, 1, Color.WHITE.getRGB(), Color.DARK_GRAY.getRGB());
			this.drawString(mc.fontRendererObj, "<", x + 11, y + 58, Color.GRAY.getRGB());
			this.drawBorderedRect(x + 75, y + 57, x + 96, y + 68, 1, Color.WHITE.getRGB(), Color.DARK_GRAY.getRGB());
			this.drawString(mc.fontRendererObj, ">", x + 85, y + 58, Color.GRAY.getRGB());**/
		}else{
			int mody = 13;
			int mody1 = 26;
			for(ModuleBase module : Main.instance.modules){
				if(module.getType() == type){
					module.x = 78 + x;
					module.x1 = 92+x;
					module.y =6+ mody + y;
					module.y1 = 5+mody1 + y;
					this.drawBorderedGradientRect(6 + x, 6 + y + mody, 94 + x, 5 + y + mody1,1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
					this.drawString(mc.fontRendererObj, module.getName(), x + 9,8 + y + mody, Color.gray.getRGB());
					if(module.toggled() == false){
						  this.drawBorderedRect(78 + x, y + 8 + mody, 92 + x, y + 16 + mody, 1, 0xFF191919, Color.red.getRGB());
						  this.drawBorderedRect(78 + x, y + 8 + mody, 82 + x, y + 16 + mody, 1, 0xFF191919, 0xFF333333);

					 }else{
						  this.drawBorderedRect(78 + x, y + 8 + mody, 92 + x, y + 16 + mody, 1, 0xFF191919, 0xFF009900);
						  this.drawBorderedRect(88 + x, y + 8 + mody, 92 + x, y + 16 + mody, 1, 0xFF191919, 0xFF333333);
					 }
					
					
					mody += 13;
					mody1 += 13;
				}
			}
		}
	}
	

	public void drawFooter(){
		this.drawBorderedGradientRect(0, this.height - 30, this.width, this.height, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		drawFooterContent();
		this.drawString(mc.fontRendererObj, "Beta v0.5 - Dyarchy", this.width - mc.fontRendererObj.getStringWidth("Beta v0.5 - Dyarchy"), this.height - mc.fontRendererObj.FONT_HEIGHT, 0xFF333333);
	}
	public void drawFooterContent(){
		
		//Player//
			this.drawBorderedGradientRect(3, this.height - 27, 37, this.height - 3, 1, Color.black.getRGB(), !playerIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
			this.drawString(mc.fontRendererObj, "Player", 9, this.height - 13, Color.GRAY.getRGB());
			//ri.func_175042_a(new ItemStack(Items.skull, 1, 3), 12, this.height - 27);
			PLAYER.renderTexture(13, this.height - 25, 13, 13, RenderUtil.HexToRGB("#FFFFFF"));
		//Player//
			
		//World//
			this.drawBorderedGradientRect(40, this.height - 27, 74, this.height - 3, 1, Color.black.getRGB(), !worldIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
			this.drawString(mc.fontRendererObj, "World", 48, this.height - 13, Color.GRAY.getRGB());
			//ri.func_175042_a(new ItemStack(Items.filled_map), 50, this.height - 27);
			WORLD.renderTexture(50, this.height - 26, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));

		//World//
			
		//Render//
			this.drawBorderedGradientRect(77, this.height - 27, 111, this.height - 3, 1, Color.black.getRGB(), !renderIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
			this.drawString(mc.fontRendererObj, "Render", 83, this.height - 13, Color.GRAY.getRGB());
			//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
			RENDER.renderTexture(86, this.height - 26, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));

		//Render//
		//combat//
		this.drawBorderedGradientRect(114, this.height - 27, 149, this.height - 3, 1, Color.black.getRGB(), !combatIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
		//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		this.drawString(mc.fontRendererObj, "Combat", 120, this.height - 13, Color.GRAY.getRGB());
		//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
		COMBAT.renderTexture(125, this.height - 26, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));
		//combat//
		
		//misc//
		this.drawBorderedGradientRect(151, this.height - 27, 186, this.height - 3, 1, Color.black.getRGB(), !miscIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
		//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		this.drawString(mc.fontRendererObj, "Misc", 161, this.height - 13, Color.GRAY.getRGB());
		//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
		MISC.renderTexture(161, this.height - 26, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));
		//misc//
		//radar//
		this.drawBorderedGradientRect(188, this.height - 27, 223, this.height - 3, 1, Color.black.getRGB(), !radarIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
		//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		this.drawString(mc.fontRendererObj, "Radar", 197, this.height - 13, Color.GRAY.getRGB());
		//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
		RADAR.renderTexture(199, this.height - 25, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));
		//radar//
		//array//
		this.drawBorderedGradientRect(225, this.height - 27, 260, this.height - 3, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
		//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		this.drawString(mc.fontRendererObj, "Array", 233, this.height - 13, Color.GRAY.getRGB());
		//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
		ARRAY.renderTexture(235, this.height - 25, 14, 14, RenderUtil.HexToRGB("#FFFFFF"));
		//array//
		/**
		//array//
		this.drawBorderedGradientRect(262, this.height - 27, 297, this.height - 3, 1, Color.black.getRGB(), !mediaIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
		//this.drawBorderedGradientRect(5, this.height - 28, 35, this.height - 2, 1, Color.black.getRGB(), 0xFF191919, 0xFF333333);
		this.drawString(mc.fontRendererObj, "Media", 270, this.height - 13, Color.GRAY.getRGB());
		//ri.func_175042_a(new ItemStack(Blocks.ice), 86, this.height - 27);
		MEDIA.renderTexture(271, this.height - 25, 14, 14, Color.white);
		//array//**/
		
		//Testing slider
		//int x = 270;
		
		if(flyValue < 0 || flyValue > 100){
			if(flyValue < 0){
				flyValue = 0;
			}
			if(flyValue > 100){
				flyValue = 100;
			}
		}
		/**
		//g.drawRoundedRect(x, this.height - 27, x + 100, this.height - 20, 5, Color.DARK_GRAY.getRGB());
		this.drawBorderedGradientRect(flyX, flyY, flyX + 110, flyY + 10, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());

		this.drawBorderedGradientRect(flyX + flyValue, flyY, flyX + 10 + flyValue, flyY + 10, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, 0xFF666666);
		
		this.drawString(mc.fontRendererObj, "" + flyValue, flyX + 50, flyY, Color.WHITE.getRGB());
		**/

	}
	
	public void drawScreen(int i, int j, float f){
		/**if(mm.getStatus() == mm.PAUSED){
			mediaIsPaused = true;
		}else if(mm.getStatus() == mm.PLAYING){
			mediaIsPaused = false;
		}else if(mm.getStatus() == mm.STOPPED){
			mediaIsPaused = true;
		}**/
		this.drawDefaultBackground();
		drawFooter();
		drawHeader(Catagory.PLAYER, PLAYER, "Player", playerx, playery, false, false, playerIsOpen, playerIsVisible);
		drawHeader(Catagory.WORLD, WORLD, "World", worldx, worldy, false, false, worldIsOpen, worldIsVisible);
		drawHeader(Catagory.RENDER, RENDER, "Render", renderx, rendery, false, false, renderIsOpen, renderIsVisible);
		drawHeader(Catagory.COMBAT, COMBAT, "Combat", combatx, combaty, false, false, combatIsOpen, combatIsVisible);
		drawHeader(Catagory.MISC, MISC, "Misc", miscx, miscy, false, false, miscIsOpen, miscIsVisible);
		drawHeader(Catagory.RADAR, RADAR, "Radar", radarx, radary, true, radarIsPinned, radarIsOpen, radarIsVisible);
		drawHeader(Catagory.ARRAYLIST, ARRAY, "Array", arrayx, arrayy, true, arrayIsPinned, arrayIsOpen, arrayIsVisible);
		//drawHeader(Catagory.MEDIA, MEDIA, "Media", mediax, mediay, true, mediaIsPinned, mediaIsOpen, mediaIsVisible);

		/**if(settingsIsOpen == false){
			this.drawBorderedGradientRect(this.width - 35, this.height - 60, this.width, this.height - 29, 1, Color.black.getRGB(),  Color.DARK_GRAY.getRGB(), Color.black.getRGB());
			//this.drawBorderedRect(this.width - 25, this.height - 70, this.width, this.height - 29, 1, Color.black.getRGB(), Color.gray.getRGB());
		
			SETTINGS.renderTexture(this.width - 33, this.height - 60, 31, 31, RenderUtil.HexToRGB("#FFFFFF"));
		}**/
		/** setTheme(Theme.DEFAULT);
		//g.drawRoundedRect(3, 3, 200, 25, 5, Color.GRAY.getRGB());
		  this.drawBorderedGradientRect(3, 3, 100, 15, 1, Color.BLACK.getRGB(), 0xFF191919, 0xFF333333);
		  this.drawString(mc.fontRendererObj, "Player", mc.fontRendererObj.getStringWidth("Player"), 5,Color.GRAY.getRGB());
		 // ri.func_180450_b(new ItemStack(Items.skull, 1, 3), 3, 3);
		  ri.func_175042_a(new ItemStack(Items.skull, 1, 3), 3, 1);
		  //ri.func_175043_b(new ItemStack(Items.skull, 1, 3));**/
		
		modeDrag(i, j);
		mouseMoved(i,j);
		super.drawScreen(i, j, f);

	}	
	
	/*public void setTheme(Theme theme){
		switch(theme){
		case DEFAULT:
			themeColorBorder = Color.BLACK.getRGB();
			themeColorInside1 = Color.GRAY.getRGB();
			themeColorInside2 = Color.DARK_GRAY.getRGB();
		case NEON_BLUE:
			themeColorBorder = Color.BLUE.getRGB();
			themeColorInside1 = Color.GRAY.getRGB();
			themeColorInside2 = Color.DARK_GRAY.getRGB();
		}
		this.theme = theme;
	}*/

	
	public void drawHover(String text, String description, int x, int y){
		  int trueWidth;
		  if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(description)){
		   trueWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
		  }else{
		   trueWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(description);
		  }
		  this.drawBorderedGradientRect(x + 8, y - 18, x + trueWidth + 18, y + 7, 1, Color.black.getRGB(),  Color.DARK_GRAY.getRGB(), Color.black.getRGB());
		  drawString(Minecraft.getMinecraft().fontRendererObj, text, x + 10, y - 16, Color.YELLOW.getRGB());
		  drawString(Minecraft.getMinecraft().fontRendererObj, description, x + 10, y - 5, Color.gray.getRGB());

	}
	
	public void mouseMoved(int x, int y){
		  if(x != mouseoldx && y != mouseoldy){
		   time = System.currentTimeMillis();
		   mouseoldx = x;
		   mouseoldy = y;
		  }else{
		   if(System.currentTimeMillis() > time + 2000){
		    for(ModuleBase module : Main.instance.modules){
		     //  if(85 + optionx < i && 98 + optionx > i && 90 + optiony < j && 105 + optiony > j){
		     //mc.thePlayer.showPlayerMessage("X1: " + module.x1 - 98 + " X: " + module.x - 83);
		     if(y < module.y1&& y > module.y&& x < module.x1&& x > module.x - 70){
		      drawHover(module.getName(), module.getDesc(), x, y);
		     }
		     
		    }
		    
		   }
		  }
		 }
	public void mouseClicked(int i, int j, int k) throws IOException{
		//MEDIA SHIT//
		//this.drawBorderedRect(x + 30, y + 57, x + 70, y + 68, 1, Color.WHITE.getRGB(), Color.DARK_GRAY.getRGB());
	//mc.thePlayer.sendPlayerMessage("test");
			
				//mm.pause();
			
		
		/**
		if(mediax + 30 < i && mediax + 70 > i && mediay + 57 < j && mediay + 68 > j){
			if(mediaIsPaused){
				mm.play();
			}else{
				mm.pause();
			}
		}

		//MEDIA SHIT//**/
		
		
		//Footer//
			//PLAYER//
			//this.drawBorderedGradientRect(3, this.height - 27, 37, this.height - 3, 1, Color.black.getRGB(), playerIsVisible ? Color.DARK_GRAY.getRGB() : 0xFFB2B2B2, Color.black.getRGB());
			if(3 < i && 37 > i && this.height - 27 < j && this.height - 3 > j){

				playerIsVisible = !playerIsVisible;
			}
			//PLAYER//
			
			
			
			//WORLD//
			//this.drawBorderedGradientRect(40, this.height - 27, 74, this.height - 3, 1, Color.black.getRGB(), !playerIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(40 < i && 74 > i && this.height - 27 < j && this.height - 3 > j){

				worldIsVisible = !worldIsVisible;
			}
			//WORLD//
			//this.drawBorderedGradientRect(77, this.height - 27, 111, this.height - 3, 1, Color.black.getRGB(), !playerIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(77 < i && 111 > i && this.height - 27 < j && this.height - 3 > j){

				renderIsVisible = !renderIsVisible;
			}
			//WORLD//
			
			//WORLD//
			//this.drawBorderedGradientRect(114, this.height - 27, 141, this.height - 3, 1, Color.black.getRGB(), !renderIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(114 < i && 141 > i && this.height - 27 < j && this.height - 3 > j){

				combatIsVisible = !combatIsVisible;
			}
			//WORLD//
			
			//MISC//
			//this.drawBorderedGradientRect(114, this.height - 27, 141, this.height - 3, 1, Color.black.getRGB(), !renderIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(151 < i && 178 > i && this.height - 27 < j && this.height - 3 > j){

				miscIsVisible = !miscIsVisible;
			}
			//MISC//
			
			//RADAR//
			//this.drawBorderedGradientRect(114, this.height - 27, 141, this.height - 3, 1, Color.black.getRGB(), !renderIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(188 < i && 223 > i && this.height - 27 < j && this.height - 3 > j){

				radarIsVisible = !radarIsVisible;
			}
			//RADAR//
			//RADAR//
			//this.drawBorderedGradientRect(225, this.height - 27, 260, this.height - 3, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(225 < i && 260 > i && this.height - 27 < j && this.height - 3 > j){

				arrayIsVisible = !arrayIsVisible;
			}
			//RADAR//
			//MEDIA
			//this.drawBorderedGradientRect(262, this.height - 27, 297, this.height - 3, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, Color.black.getRGB());
			if(262 < i && 297 > i && this.height - 27 < j && this.height - 3 > j){

				mediaIsVisible = !mediaIsVisible;
			}
			//MEDIA//
		//Footer//
		
		//Dragging//
			//TODO: test
			//this.drawBorderedGradientRect(x + 45, this.height - 27, x + 10 + 45, this.height - 17, 1, Color.black.getRGB(), !arrayIsVisible ? Color.DARK_GRAY.getRGB() : 0xFF666666, 0xFF666666);
			if(0 + flyX < i && 100 + flyX > i && 0 + flyY < j && 15 + flyY > j){
				isFlyDragging = !isFlyDragging;
			}
			//player//
			if(0 + playerx < i && 80 + playerx > i && 0 + playery < j && 15 + playery > j){

				playerIsDragging = !playerIsDragging;
			}
			//player//
			
			//world//
			if(0 + worldx < i && 80 + worldx > i && 0 + worldy < j && 15 + worldy > j){

				worldIsDragging = !worldIsDragging;
			}
			//world//
			//render//
			if(0 + renderx < i && 80 + renderx > i && 0 + rendery < j && 15 + rendery > j){

				renderIsDragging = !renderIsDragging;
			}
			//render//
			//combat//
			if(0 + combatx < i && 80 + combatx > i && 0 + combaty < j && 15 + combaty > j){

				combatIsDragging = !combatIsDragging;
			}
			//combat//
			//misc//
			if(0 + miscx < i && 80 + miscx > i && 0 + miscy < j && 15 + miscy > j){

				miscIsDragging = !miscIsDragging;
			}
			//misc//
			//radar//
			if(0 + radarx < i && 69 + radarx > i && 0 + radary < j && 15 + radary > j){

				radarIsDragging = !radarIsDragging;
			}
			//radar//
			//radar//
			if(0 + arrayx < i && 69 + arrayx > i && 0 + arrayy < j && 15 + arrayy > j){

				arrayIsDragging = !arrayIsDragging;
			}
			//radar//
			//media//
			if(0 + mediax < i && 69 + mediax > i && 0 + mediay < j && 15 + mediay > j){

				mediaIsDragging = !mediaIsDragging;
			}
			//media//
		//Dragging//
			
			
		//pinning//
			//array//
			if(70 + arrayx < i && 78 + arrayx > i && 2 + arrayy < j && 8 + arrayy > j){

				arrayIsPinned = !arrayIsPinned;
			}
			//array//
			//array//
			if(70 + radarx < i && 78 + radarx > i && 2 + radary < j && 8 + radary > j){

				radarIsPinned = !radarIsPinned;
			}
			//array//
			//media//
			if(70 + mediax < i && 78 +mediax > i && 2 + mediay < j && 8 +mediay > j){

				mediaIsPinned = !mediaIsPinned;
			}
			//media//
		//pinning//
		//Openning//
			//Player//
			if(80 + playerx < i && 96 + playerx > i && 0 + playery < j && 15 + playery > j){

				playerIsOpen = !playerIsOpen;
			}
			//Player//
			
			//World//
			if(80 + worldx < i && 96 + worldx > i && 0 + worldy < j && 15 + worldy > j){

				worldIsOpen = !worldIsOpen;
			}
			//World//
			//Render//
			if(80 + renderx < i && 96 + renderx > i && 0 + rendery < j && 15 + rendery > j){

				renderIsOpen = !renderIsOpen;
			}
			//Render//
			//Render//
			if(80 + combatx < i && 96 + combatx > i && 0 + combaty < j && 15 + combaty > j){

				combatIsOpen = !combatIsOpen;
			}
			//Render//
			
			//misc//
			if(80 + miscx < i && 96 + miscx > i && 0 + miscy < j && 15 + miscy > j){

				miscIsOpen = !miscIsOpen;
			}
			//misc//
			
			//radar//
			if(80 + radarx < i && 96 + radarx > i && 0 + radary < j && 15 + radary > j){

				radarIsOpen = !radarIsOpen;
			}
			//radar//
			//radar//
			if(80 + arrayx < i && 96 + arrayx > i && 0 + arrayy < j && 15 + arrayy > j){

				arrayIsOpen = !arrayIsOpen;
			}
			//radar//
			//media//
			if(80 + mediax < i && 96 + mediax > i && 0 + mediay < j && 15 + mediay > j){

				mediaIsOpen = !mediaIsOpen;
			}
			//media//
		//Openning//
			
			
			for(ModuleBase module : Main.instance.modules){
			     if(j < module.y1 - 2&& j > module.y&& i < module.x1&& i > module.x){
					//mc.thePlayer.sendPlayerMessage("Module x: " + module.x + " module x1: " + module.x1 + "Module y: " + module.y + "Module y1: " + module.y1);
					module.toggle();
					if(module.getName() != "GUI"){
			            if(module.toggled()){
			            	mc.ingameGUI.Notify("Enabled '" + module.getName() + "'", 120);
			            }else{
			            	mc.ingameGUI.Notify("Disabled '" + module.getName() + "'", 120);
			            }
			           }
				}
			}
		
		super.mouseClicked(i, j, k);

	}

	
	public void modeDrag(int i, int j){
		if(isFlyDragging){
			if(flyValue >= 0 && flyValue <= 100){
				flyValue = i - 15;
			}else{
				if(flyValue >= 10){
					flyValue = 100;
				}
				if(flyValue <= 0){
					flyValue = 0;
				}
			}
			
		}
		if(mediaIsDragging){
			mediax = i - 50;
			mediay = j - 5;
		}
		if(playerIsDragging){
			playerx = i - 50;
			playery = j - 5;
		}
		if(worldIsDragging){
			worldx = i - 50;
			worldy = j - 5;
		}
		if(renderIsDragging){
			renderx = i - 50;
			rendery = j - 5;
		}
		if(combatIsDragging){
			combatx = i - 50;
			combaty = j - 5;
		}
		if(miscIsDragging){
			miscx = i - 50;
			miscy = j - 5;
		}
		if(radarIsDragging){
			radarx = i - 50;
			radary = j - 5;
		}
		if(arrayIsDragging){
			arrayx = i - 50;
			arrayy = j - 5;
		}
	}

}