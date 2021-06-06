package net.gooby.ass.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class CustomButton extends GuiButton {
 
    public CustomButton(int i, int j, int k, String s)
    {
        this(i, j, k, 120, 20, s);
    }
 
    public CustomButton(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
    }
 
    protected int getHoverState(boolean flag)
    {
        byte byte0 = 1;
        if (!enabled)
        {
                byte0 = 0;
                }
        else if (flag)
        {
                byte0 = 2;
        }
        return byte0;
    }
 
    public void drawButton(Minecraft mc, int mx, int my)
    {

        FontRenderer fontrenderer = mc.fontRendererObj;
        boolean flag = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;  //Flag, tells if your mouse is hovering the button
        if (flag)
        { // Hover Action
                drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0xFF2A0000, 0x80000000);
                drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xFF059DA9);
        }
        else { // Normal
                drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0xFF2A0000, 0x60000000);
                drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xFF00ECFF);
        }
    }
 
}
