package net.gooby.ass.gui;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.lwjgl.input.Keyboard;

import net.gooby.ass.auth.YggdrasilAuthenticator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiAccount
  extends GuiScreen
{
  private Minecraft mc = Minecraft.getMinecraft();
  private GuiTextField usernameField;
  private GuiPasswordField passwordField;
  private GuiScreen parentScreen;
  
  public GuiAccount(GuiScreen parentScreen)
  {
    this.parentScreen = parentScreen;
  }
  
  public void updateScreen()
  {
    this.usernameField.updateCursorCounter();
    this.passwordField.updateCursorCounter();
    super.updateScreen();
  }
  
  public void onGuiClosed()
  {
    Keyboard.enableRepeatEvents(false);
    super.onGuiClosed();
  }
  
  protected void keyTyped(char typedChar, int keyCode)
    throws IOException
  {
    this.usernameField.textboxKeyTyped(typedChar, keyCode);
    this.passwordField.textboxKeyTyped(typedChar, keyCode);
    if (typedChar == '\t') {
      if (this.usernameField.isFocused())
      {
        this.usernameField.setFocused(false);
        this.passwordField.setFocused(true);
      }
      else
      {
        this.passwordField.setFocused(false);
        this.usernameField.setFocused(true);
      }
    }
    super.keyTyped(typedChar, keyCode);
  }
  
  public void initGui()
  {
    Keyboard.enableRepeatEvents(true);
    this.buttonList.clear();
    this.buttonList.add(new CustomButton(0, this.width / 2 - 95, this.height / 2 + 37, 190, 20, "Done"));
    this.buttonList.add(new CustomButton(1, this.width / 2 - 95, this.height / 2 + 60, 190, 20, "Cancel"));
    
    this.usernameField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 95, this.height / 2 - 50, 190, 20);
    this.usernameField.setFocused(true);
    this.passwordField = new GuiPasswordField(0, this.fontRendererObj, "Password", this.width / 2 - 95, this.height / 2 - 20, 190, 20);
    super.initGui();
  }
  
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.usernameField.mouseClicked(mouseX, mouseY, mouseButton);
    this.passwordField.mouseClicked(mouseX, mouseY, mouseButton);
    super.mouseClicked(mouseX, mouseY, mouseButton);
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {
    drawDefaultBackground();
    this.drawCenteredString(this.fontRendererObj, I18n.format("Login", new Object[0]), this.width / 2, 20, 16777215);

    this.usernameField.drawTextBox();
    this.passwordField.drawTextBox();
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
  
  protected void actionPerformed(GuiButton button)
    throws IOException
  {
    if (!button.enabled) {
      return;
    }
    switch (button.id)
    {
    case 0: 
      if (this.usernameField.getText().length() < 1) {
        return;
      }
      SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
          YggdrasilAuthenticator Auth = new YggdrasilAuthenticator(GuiAccount.this.usernameField.getText(), GuiAccount.this.passwordField.getText());
        }
      });
      this.mc.displayGuiScreen(this.parentScreen);
      break;
    case 1: 
      this.mc.displayGuiScreen(this.parentScreen);
      break;
    }
    super.actionPerformed(button);
  }
}