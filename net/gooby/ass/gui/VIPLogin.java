package net.gooby.ass.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import javax.swing.JOptionPane;

import net.gooby.ass.src.HandleConsole;
import net.gooby.ass.src.Mods;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

public class VIPLogin extends GuiScreen {
	private GuiScreen parentScreen;
	private GuiTextField playerUsername;
	private GuiTextField playerPassword;
	private static final String __OBFID = "CL_00000695";

	public void GuiScreenAddServer(GuiScreen p_i1033_1_, ServerData p_i1033_2_) {
		this.parentScreen = p_i1033_1_;
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		this.playerUsername.updateCursorCounter();
		this.playerPassword.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(
				new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 18, I18n.format("Login", new Object[0])));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 18,
				I18n.format("Cancel", new Object[0])));
		this.playerUsername = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, 66, 200, 20);
		this.playerUsername.setFocused(true);
		this.playerPassword = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, 106, 200, 20);
		this.playerPassword.setMaxStringLength(128);
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat
	 * events
	 */
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == 1) {
				this.mc.displayGuiScreen(parentScreen);
			} else if (button.id == 0) {
				/*
				 * this.serverData.playerUsername =
				 * this.playerUsername.getText(); this.serverData.playerPassword
				 * = this.playerPassword.getText();
				 */

				if (this.playerUsername.getText().length() > 0 && this.playerPassword.getText().length() > 0) {
						try {
							URL url = new URL(
									"http://goobychan.zz.mu/Notorious/login.php?user=" + this.playerUsername.getText());
							BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
							String output = in.readLine();
							in.close();
							if (output.contains("Fatal error")) {
								Mods.isVIP = false;
								Mods.vipText = null;
								this.mc.displayGuiScreen(parentScreen);
							} else {
								String[] outSplit;
								outSplit = output.split(":");

								if (this.playerUsername.getText().equals(outSplit[0].replaceAll("ï»¿", ""))
										&& this.playerPassword.getText().equals(outSplit[1])) {
									
									Mods.isVIP = true;
									Mods.vipText = "§atrue";
									System.out.println(
											this.playerUsername.getText() + " | " + this.playerPassword.getText());
									this.mc.displayGuiScreen(parentScreen);
								} else {
									Mods.isVIP = false;
									Mods.vipText = null;
									// System.out.println(outSplit[0] + " | " +
									// outSplit[1]);
									this.mc.displayGuiScreen(parentScreen);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					// this.mc.displayGuiScreen(parentScreen);
				} else {
					JOptionPane.showMessageDialog(null, "Please fill in a username and password...");
				}
			}
		}
	}

	/**
	 * Fired when a key is typed (except F11 who toggle full screen). This is
	 * the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character
	 * (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		this.playerUsername.textboxKeyTyped(typedChar, keyCode);
		this.playerPassword.textboxKeyTyped(typedChar, keyCode);

		if (keyCode == 15) {
			this.playerUsername.setFocused(!this.playerUsername.isFocused());
			this.playerPassword.setFocused(!this.playerPassword.isFocused());
		}

		if (keyCode == 28 || keyCode == 156) {
			this.actionPerformed((GuiButton) this.buttonList.get(0));
		}

		// ((GuiButton)this.buttonList.get(0)).enabled =
		// this.playerPassword.getText().length() > 0 &&
		// this.playerUsername.getText().length() > 0;
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.playerPassword.mouseClicked(mouseX, mouseY, mouseButton);
		this.playerUsername.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY,
	 * renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("VIP Login", new Object[0]), this.width / 2, 17,
				16777215);
		this.drawString(this.fontRendererObj, I18n.format("Username", new Object[0]), this.width / 2 - 100, 53,
				10526880);
		this.drawString(this.fontRendererObj, I18n.format("Password", new Object[0]), this.width / 2 - 100, 94,
				10526880);
		this.playerUsername.drawTextBox();
		this.playerPassword.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
