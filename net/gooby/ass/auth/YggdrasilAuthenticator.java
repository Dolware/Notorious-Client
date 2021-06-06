package net.gooby.ass.auth;


import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class YggdrasilAuthenticator
{
  public YggdrasilPayload Payload = new YggdrasilPayload();
  public String Username;
  public String Password;
  
  public YggdrasilAuthenticator(String Username, String Password)
  {
    this.Username = Username;
    this.Password = Password;
    if ((Password != "") && (Password != null))
    {
      Session AuthResponse = YggdrasilPayload.loginPassword(this.Username, this.Password);
      Minecraft.getMinecraft().session = AuthResponse;
    }
    else
    {
      Session AuthResponseCrack = YggdrasilPayload.loginCrack(this.Username);
      Minecraft.getMinecraft().session = AuthResponseCrack;
    }
  }
}
