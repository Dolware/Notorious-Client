package net.gooby.ass.utils;

import java.nio.IntBuffer;

import net.minecraft.client.gui.FontRenderer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * 
 * @author Michael M. (alias: Mike724/Mike724s)
 * @version 1.0
 * @since February 27th 2012
 */
public class GuiUtils {
	
	/* ##### RECTANGLE METHODS ##### */
    public static void drawBorderedRect(int x, int y, int x1, int y1, int color, float lineWidth, int color1)
    {
    	drawRect(x,y,x1,y1,color);
    	setupOverlayRendering();
    	disableDefaults();
    	GL11.glColor4d(getRedFromHex(color1),getGreenFromHex(color1),getBlueFromHex(color1),getAlphaFromHex(color1));
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINES);
        	GL11.glVertex2d(x, y);
    		GL11.glVertex2d(x, y1);
    		GL11.glVertex2d(x1, y1);
    		GL11.glVertex2d(x1, y);
    		GL11.glVertex2d(x,y);
    		GL11.glVertex2d(x1,y);
    		GL11.glVertex2d(x,y1);
    		GL11.glVertex2d(x1,y1);
    	GL11.glEnd();
    	enableDefaults();
    	
    }
	public static void drawRect(int x, int y, int x1, int y1, int color)
	{
		setupOverlayRendering();
		disableDefaults();
		GL11.glColor4d(getRedFromHex(color),getGreenFromHex(color),getBlueFromHex(color),getAlphaFromHex(color));
        GL11.glBegin(GL11.GL_QUADS);
        	GL11.glVertex2i(x1, y);
        	GL11.glVertex2i(x, y);
        	GL11.glVertex2i(x, y1);
        	GL11.glVertex2i(x1, y1);
        GL11.glEnd();
        enableDefaults();
	}
	public static void drawRoundedRect(int x, int y, int x1, int y1, int radius, int color)
	{
		setupOverlayRendering();
		disableDefaults();
		int newX = Math.abs(x+radius);
		int newY = Math.abs(y+radius);
		int newX1 = Math.abs(x1-radius);
		int newY1 = Math.abs(y1-radius);
		double r = getRedFromHex(color);
		double g = getGreenFromHex(color);
		double b = getBlueFromHex(color);
		double a = getAlphaFromHex(color);
		
		//Draw main (center) rectangle
		drawRect(newX, newY, newX1, newY1, color);
		
		/* Draw outside rectangles */
		//Left rectangle
		drawRect(x, newY, newX, newY1, color);
		//Right rectangle
		drawRect(newX1, newY, x1, newY1, color);
		//Top rectangle
		drawRect(newX, y, newX1, newY, color);
		//Bottom rectangle
		drawRect(newX, newY1, newX1, y1, color);
		
		//Draw curves
		drawQuarterCircle(newX,newY,radius,0,color);
		drawQuarterCircle(newX1,newY,radius,1,color);
		drawQuarterCircle(newX,newY1,radius,2,color);
		drawQuarterCircle(newX1,newY1,radius,3,color);
		enableDefaults(); 
	}
	
	
	/* ##### UNICODE STRING METHODS ##### */
	public static void drawCenteredString(FontRenderer font, int x, int y, String text, int color)
	{
		drawString(font, x-(font.getStringWidth(text)/2), y, text, color);
	}
	public static void drawString(FontRenderer font, int x, int y, String text, int color)
	{
		setupOverlayRendering();
		disableDefaults();
		font.drawString(text, x, y, color);
		enableDefaults();
	}
	
	
	/* ##### 2D LINE METHODS ##### */
	public static void drawLine2D(int x, int y, int x1, int y1, int color, float lineWidth)
	{
		setupOverlayRendering();
		disableDefaults();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glColor4d(getRedFromHex(color),getGreenFromHex(color),getBlueFromHex(color),getAlphaFromHex(color));
		GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex2i(x, y);
			GL11.glVertex2i(x1, y1);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		enableDefaults();
	}
	
	
	/* ##### CIRCLE METHODS ##### */
	public static void drawBorderedCircle(int x, int y, int radius, int color, float lineWidth, int color1)
	{
		drawCircle(x,y,radius,color);
		drawUnfilledCircle(x,y,radius,lineWidth,color1);
	}
	public static void drawUnfilledCircle(int x, int y, int radius, float lineWidth, int color)
	{
		//setupOverlayRendering();
		disableDefaults();
		GL11.glColor4d(getRedFromHex(color),getGreenFromHex(color),getBlueFromHex(color),getAlphaFromHex(color));
		GL11.glLineWidth(lineWidth);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i <= 360; i++) {
			GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
		}
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		enableDefaults();
	}
	public static void drawCircle(int x, int y, int radius, int color)
	{
		//setupOverlayRendering();
		disableDefaults();
		GL11.glColor4d(getRedFromHex(color),getGreenFromHex(color),getBlueFromHex(color),getAlphaFromHex(color));
		GL11.glBegin(GL11.GL_POLYGON);
		for (int i = 0; i <= 360; i++) {
			GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
		}
		GL11.glEnd();
		enableDefaults();
	}
	//Modes:
	// 0 = Top Left
	// 1 = Top Right
	// 2 = Bottom Left
	// 3 = Bottom Right
	public static void drawQuarterCircle(int x, int y, int radius, int mode, int color)
	{
		setupOverlayRendering();
		disableDefaults();
		GL11.glColor4d(getRedFromHex(color),getGreenFromHex(color),getBlueFromHex(color),getAlphaFromHex(color));
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2d(x, y);
		if(mode==0) {
			for (int i = 0; i <= 90; i++) {
				GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * (radius*-1)), y + (Math.cos((i * 3.141526D / 180)) * (radius*-1)));
			}
		} else if(mode==1) {
			for (int i = 90; i <= 180; i++) {
				GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
			}
		} else if(mode==2) {
			for (int i = 90; i <= 180; i++) {
				GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * (radius*-1)), y + (Math.cos((i * 3.141526D / 180)) * (radius*-1)));
			}
		} else if(mode==3) {
			for (int i = 0; i <= 90; i++) {
				GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
			}
		} else {
		}
		GL11.glEnd();
		enableDefaults(); 
	}
	
	
	/* ##### UTILITY METHODS ##### */
	public static double getAlphaFromHex(int color)
	{
		return((double)((color >> 24 & 0xff) / 255F));
	}
	public static double getRedFromHex(int color)
	{
		return((double)((color >> 16 & 0xff) / 255F));
	}
	public static double getGreenFromHex(int color)
	{
		return((double)((color >> 8 & 0xff) / 255F));
	}
	public static double getBlueFromHex(int color)
	{
		return((double)((color & 0xff) / 255F));
	}
    public static int getScreenWidth()
    {
    	IntBuffer viewport = BufferUtils.createIntBuffer(16);
    	GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
    	return(Math.round(viewport.get(2)));
    }
    
    public static int getScreenHeight()
    {
    	IntBuffer viewport = BufferUtils.createIntBuffer(16);
    	GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
    	return(Math.round(viewport.get(3)));
    }
	public static void setupGradient()
	{
        GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glShadeModel(7425 /*GL_SMOOTH*/);
	}
	public static void unsetupGradient()
	{
        GL11.glShadeModel(7424 /*GL_FLAT*/);
        GL11.glDisable(3042 /*GL_BLEND*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
	}
    public static void setupOverlayRendering()
    {
        GL11.glClear(256);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, getScreenWidth(), getScreenHeight(), 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(5888 /*GL_MODELVIEW0_ARB*/);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
    }
    public static void disableDefaults()
    {
		GL11.glEnable(GL11.GL_BLEND);
        //GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
    public static void enableDefaults()
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
       // GL11.glEnable(GL11.GL_LIGHTING);
    }
    public static void disableLighting()
    {
    	GL11.glDisable(GL11.GL_LIGHTING);
    }
}