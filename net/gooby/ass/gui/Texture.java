package net.gooby.ass.gui;

import java.awt.Color;

import net.gooby.ass.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public final class Texture
{
  private ResourceLocation texture;
  
  public Texture(String textureURL)
  {
    this.texture = new ResourceLocation(textureURL);
    Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
  }
  
  public void renderTexture(double x, double y, double width, double height, Color c)
  {
    if (this.texture != null)
    {
      Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
      GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
      RenderUtil.renderTexture(x, y, width, height);
      
    }
  }
  
  public void bindTexture()
  {
    if (this.texture != null) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
    }
  }
  
  public void setTextue(String textureURL)
  {
    this.texture = new ResourceLocation(textureURL);
    Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
  }
}