package net.gooby.ass.auth;


import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import java.util.UUID;
import net.minecraft.util.Session;

public class YggdrasilPayload
{
  private static String errorMessage = "";
  
  public static Session loginPassword(String username, String password)
  {
    if ((username == null) || (username.length() <= 0) || (password == null) || (password.length() <= 0)) {
      return null;
    }
    YggdrasilAuthenticationService a = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
    YggdrasilUserAuthentication b = (YggdrasilUserAuthentication)a.createUserAuthentication(Agent.MINECRAFT);
    b.setUsername(username);
    b.setPassword(password);
    try
    {
      b.logIn();
      return new Session(b.getSelectedProfile().getName(), b.getSelectedProfile().getId().toString(), b.getAuthenticatedToken(), "mojang");
    }
    catch (AuthenticationException e)
    {
      e.printStackTrace();
      errorMessage = e.getLocalizedMessage();
    }
    return null;
  }
  
  public static Session loginCrack(String username)
  {
    return new Session(username, "", "", "mojang");
  }
  
  public static String getPayloadError()
  {
    return errorMessage;
  }
}
