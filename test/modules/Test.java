package test.modules;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.src.Catagory;
import net.gooby.ass.utils.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;

public class Test extends ModuleBase{
	
	public Test()
	{
		super("Test", "Test Module for Deving", Keyboard.KEY_UNLABELED, 0, Catagory.MISC);
	}
	
	public void toggleEvent()
	{
		if(this.toggled())
		{
			this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, Integer.MAX_VALUE, 3));
			//mc.ingameGUI.Notify("Enabled '"+this.getName()+"'", 80);
			
		}else{
			this.mc.thePlayer.removePotionEffect(Potion.moveSpeed.id);
			//mc.ingameGUI.Notify("Disabled '"+this.getName()+"'", 80);
		}
	}
	
	public void onRender()
	{
		for(Object test : this.mc.theWorld.loadedTileEntityList)
		{
			if(test instanceof TileEntityChest)
			{
				TileEntityChest chest = (TileEntityChest)test;
				chest.openInventory(this.mc.thePlayer);
				RenderUtil.drawNukerESP(chest.getPos().getX() - this.mc.getRenderManager().renderPosX, chest.getPos().getY() - this.mc.getRenderManager().renderPosY, chest.getPos().getZ() - this.mc.getRenderManager().renderPosZ);
			}
		}
	}

}
