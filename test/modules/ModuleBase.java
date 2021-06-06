package test.modules;

import net.gooby.ass.src.Catagory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;

public class ModuleBase {
	
	public static boolean nowaterOn, nofallOn;
	
	private String moduleName, moduleDesc;
	private int moduleBind, moduleColor;
	public int x;
	public int y;
	public int x1;
	public int y1;
	private Catagory type;
	private boolean enabled;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public ModuleBase(String moduleName, String moduleDesc, int moduleBind, int moduleColor, Catagory type){
		this.moduleName = moduleName;
		this.moduleBind = moduleBind;
		this.moduleColor = moduleColor;
		this.moduleDesc = moduleDesc;
		this.type = type;
	}
	
	public void showNote()
	{
		if(this.toggled()){
			mc.ingameGUI.Notify("Enabled '" + this.getName() + "'", 120);
		}else{
			mc.ingameGUI.Notify("Disabled '" + this.getName() + "'", 120);
		}
	}
	
	public void tick(){}
	
	public void toggle(){
		this.enabled = !this.enabled;
		toggleEvent();
	}
	
	public void setToggled(boolean toggle)
	{
		this.enabled = toggle;
		toggleEvent();
	}
	
	
	public void toggleEvent(){}
	
	public void onRender(){}
	
	public void onUpdate(){}
	
	public String getName()
	{
		return this.moduleName;
	}
	
	public String getDesc()
	{
		return this.moduleDesc;
	}
	
	public int getColor()
	{
		return this.moduleColor;
	}
	
	public Catagory getType()
	{
		return this.type;
	}
	
	public int getBind()
	{
		return this.moduleBind;
	}
	
	public boolean toggled()
	{
		return this.enabled;
	}
	
	public void setName(String modName)
	{
		this.moduleName = modName;
	}
	
	public void setDesc(String modDesc)
	{
		this.moduleDesc = modDesc;
	}
	
	public void setBind(int modBind)
	{
		this.moduleBind = modBind;
	}
	
	public void setColor(int modColor)
	{
		this.moduleColor = modColor;
	}

}
