package net.gooby.ass.utils;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.AxisAlignedBB;
import test.modules.Main;

public class RenderUtil extends Gui{
	
	  private Minecraft mc = Minecraft.getMinecraft();
	  private static Main cn = Main.instance;
	  
	  public static void drawOutlinedBoundingBox(AxisAlignedBB aa)
	  {
	    Tessellator tt = Tessellator.getInstance();
	    WorldRenderer t = tt.getWorldRenderer();
	    t.startDrawing(3);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    tt.draw();
	    t.startDrawing(3);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    tt.draw();
	    t.startDrawing(1);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    tt.draw();
	  }
	  
	  public static void drawAALine(AxisAlignedBB aa)
	  {
	    Tessellator tt = Tessellator.getInstance();
	    WorldRenderer t = tt.getWorldRenderer();
	    t.startDrawing(3);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    tt.draw();
	  }
	  
	  public static void drawBoundingBox(AxisAlignedBB aa)
	  {
	    Tessellator tt = Tessellator.getInstance();
	    WorldRenderer t = tt.getWorldRenderer();
	    t.startDrawingQuads();
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    tt.draw();
	    t.startDrawingQuads();
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    tt.draw();
	    t.startDrawingQuads();
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    tt.draw();
	    t.startDrawingQuads();
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    tt.draw();
	    t.startDrawingQuads();
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    tt.draw();
	    t.startDrawingQuads();
	    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.minX, aa.minY, aa.maxZ);
	    t.addVertex(aa.minX, aa.maxY, aa.minZ);
	    t.addVertex(aa.minX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
	    t.addVertex(aa.maxX, aa.minY, aa.minZ);
	    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
	    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
	    tt.draw();
	  }
	  
	  public static void drawNukerESP(double d, double d1, double d2)
	  {
	    GL11.glPushMatrix();
	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);
	    GL11.glLineWidth(1.0F);
	    GL11.glDisable(2896);
	    GL11.glDisable(3553);
	    GL11.glEnable(2848);
	    GL11.glDisable(2929);
	    GL11.glDepthMask(false);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
	    drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
	    GL11.glLineWidth(2.0F);
	    GL11.glDisable(2848);
	    GL11.glEnable(3553);
	    GL11.glEnable(2896);
	    GL11.glEnable(2929);
	    
	    GL11.glDepthMask(true);
	    GL11.glDisable(3042);
	    GL11.glPopMatrix();
	  }
	  
	  public static void drawESP(double d, double d1, double d2)
	  {
	    GL11.glPushMatrix();
	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);
	    GL11.glLineWidth(1.0F);
	    GL11.glDisable(2896);
	    GL11.glDisable(3553);
	    GL11.glEnable(2848);
	    GL11.glDisable(2929);
	    GL11.glDepthMask(false);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
	    drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
	    GL11.glLineWidth(1.0F);
	    
	    GL11.glDisable(2848);
	    
	    GL11.glEnable(3553);
	    
	    GL11.glEnable(2896);
	    GL11.glEnable(2929);
	    
	    GL11.glDepthMask(true);
	    GL11.glDisable(3042);
	    GL11.glPopMatrix();
	  }
	  
	  public static void drawChestESP(double d, double d1, double d2, double r, double g, double b)
	  {
	    GL11.glPushMatrix();
	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);
	    GL11.glLineWidth(1.0F);
	    GL11.glDisable(2896);
	    GL11.glDisable(3553);
	    GL11.glEnable(2848);
	    GL11.glDisable(2929);
	    GL11.glDepthMask(false);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
	    drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
	    GL11.glLineWidth(1.0F);
	    
	    GL11.glDisable(2848);
	    
	    GL11.glEnable(3553);
	    
	    GL11.glEnable(2896);
	    GL11.glEnable(2929);
	    
	    GL11.glDepthMask(true);
	    GL11.glDisable(3042);
	    GL11.glPopMatrix();
	  }
	  
	  public static void renderTexture(double x, double y, double width, double height)
	   {
	     boolean tex2D = GL11.glGetBoolean(3553);
	     boolean blend = GL11.glGetBoolean(3042);
	     GL11.glPushMatrix();
	     GL11.glEnable(3042);
	     GL11.glEnable(3553);
	     GL11.glBlendFunc(770, 771);
	     GL11.glScalef(0.5F, 0.5F, 0.5F);
	     x *= 2.0D;
	     y *= 2.0D;
	     width *= 2.0D;
	     height *= 2.0D;
	     GL11.glBegin(4);
	     GL11.glTexCoord2f(1.0F, 0.0F);
	     GL11.glVertex2d(x + width, y);
	     GL11.glTexCoord2f(0.0F, 0.0F);
	     GL11.glVertex2d(x, y);
	     GL11.glTexCoord2f(0.0F, 1.0F);
	     GL11.glVertex2d(x, y + height);
	     GL11.glTexCoord2f(0.0F, 1.0F);
	     GL11.glVertex2d(x, y + height);
	     GL11.glTexCoord2f(1.0F, 1.0F);
	     GL11.glVertex2d(x + width, y + height);
	     GL11.glTexCoord2f(1.0F, 0.0F);
	     GL11.glVertex2d(x + width, y);
	     GL11.glEnd();
	     if (!tex2D) {
	       GL11.glDisable(3553);
	     }
	     if (!blend) {
	       GL11.glDisable(3042);
	     }
	     GL11.glPopMatrix();
	   }
	  
	  public static void drawPlayerESP(double d, double d1, double d2, EntityOtherPlayerMP thePlayer, double e, double f)
	  {
	    GL11.glPushMatrix();
	    GL11.glEnable(3042);
	    GL11.glDisable(3553);
	    GL11.glDisable(2896);
	    GL11.glDisable(2929);
	    GL11.glDepthMask(false);
	    GL11.glLineWidth(1.0F);
	    GL11.glBlendFunc(770, 771);
	    GL11.glEnable(2848);
	    
	    //GL11.glColor4f(RED, GREEN, BLUE, ALPHA);
	    if(Main.instance.fr.friendList.contains(thePlayer.getName()))
	    {
	    	GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
	    }else if(Main.instance.fr.enemyList.contains(thePlayer.getName()))
	    {
	    	GL11.glColor4f(1.0F,0.0F, 0.0F, 1.0F);
	    }else{
	    	GL11.glColor4f(1.0F,1.0F, 1.0F, 0.65F);
	    }
	    
	    drawOutlinedBoundingBox(new AxisAlignedBB(d - f, d1 + 0.1D, d2 - f, d + f, d1 + e + 0.25D, d2 + f));
	    GL11.glDepthMask(true);
	    GL11.glEnable(2929);
	    GL11.glEnable(3553);
	    GL11.glEnable(2896);
	    GL11.glDisable(2848);
	    GL11.glDisable(3042);
	    GL11.glPopMatrix();
	  }
	  
	  public static Color HexToRGB(String colorStr)
	  {
	    return new Color(Integer.valueOf(colorStr.substring(1, 3), 16).intValue(), Integer.valueOf(colorStr.substring(3, 5), 16).intValue(), Integer.valueOf(colorStr.substring(5, 7), 16).intValue());
	  }
	  /**
	  public static void changeColor(String code)
	  {
	    try
	    {
	      cn.colorCode = code;
	      cn.fileManager.save("ColorCode");
	      Color color = HexToRGB(code);
	      cn.colorR = ((float)(color.getRed() / 255.0D));
	      cn.colorG = ((float)(color.getGreen() / 255.0D));
	      cn.colorB = ((float)(color.getBlue() / 255.0D));
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }**/
}
