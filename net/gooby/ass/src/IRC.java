package net.gooby.ass.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class IRC implements Runnable{
	  BufferedWriter writer;
	  BufferedReader reader;
		private static Minecraft mc = Minecraft.getMinecraft();
		public static EntityPlayerSP ep = mc.thePlayer;
	 
	 String server;
	 String nick;
	 String login;
	 String channel;
	 int port;
	 
		public static void sendPlayerMessage(String input)
		{
			ep.addChatComponentMessage(new ChatComponentText(new String(Mods.ircPrefix + input).replaceAll("&", "§")));
		}
	 
	public void irc(String server, String nick, String login, String channel, int port){
		this.server = server;
		this.nick = nick;
		this.login = login;
		this.channel = channel;
		this.port = port;
	}
	
	public void connect(){
		try{
			 // The server to connect to and our details.

	        // Connect directly to the IRC server.
	        Socket socket = new Socket(server, port);
	         writer = new BufferedWriter(
	                new OutputStreamWriter(socket.getOutputStream( )));
	         reader = new BufferedReader(
	                new InputStreamReader(socket.getInputStream( )));
	        
	        // Log on to the server.
	        writer.write("NICK " + nick + "\r\n");
	        writer.write("USER " + login + " 8 * : Notorious\r\n");
	        writer.flush( );
	        
	        // Read lines from the server until it tells us we have connected.
	        String line = null;
	        while ((line = reader.readLine( )) != null) {
	            if (line.indexOf("004") >= 0) {
	                // We are now logged in.
	            	this.sendPlayerMessage("You are now logged in as '"+mc.thePlayer.getName()+"'!");
	                break;
	            }
	            else if (line.indexOf("433") >= 0) {
	            	this.sendPlayerMessage("The name '"+mc.thePlayer.getName()+"' is already in use!");
	                System.out.println("Nickname is already in use.");
	                return;
	            }
	        }
	        
	        // Join the channel.
	        writer.write("JOIN " + channel + "\r\n");
	        writer.flush( );
	        
	        // Keep reading lines from the server.
	        while ((line = reader.readLine( )) != null) {
	            if (line.toLowerCase( ).startsWith("PING ")) {
	                // We must respond to PINGs to avoid being disconnected.
	                writer.write("PONG " + line.substring(5) + "\r\n");
	                writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
	                writer.flush( );
	            }
	            
	            else {
	                // Print the raw line received by the bot.
	            	if(line.contains("PRIVMSG ##notoriousmc :"))
	            	{
	            		String[] ircMsg = line.split("PRIVMSG ##notoriousmc :");
	            		String[] ircUser = line.split("@");
	            		String[] ircUsername = ircUser[0].split("!~");
	            		ircUsername[1] = ircUsername[1].replaceAll("[!:~]", "");
	            		//ircUser[0] = ircUser[0].replaceAll("[!:~]", "");
	            		/*ircUser[0].replaceAll(":", "");
	            		ircUser[0].replaceAll("!", "");
	            		ircUser[0].replaceAll("~", "");*/
	            		
	            		sendPlayerMessage("<"+ircUsername[1]+">"+ircMsg[1]);
	            		//System.out.println(ircMsg[0]);
	            	}
	            	this.sendPlayerMessage("");
	                System.out.println(line);
	            }
	        }
	    
		}catch(Exception e){
			
		}
	}
	
	public void sendMessage(String message){
		try {
			writer.write("PRIVMSG " + channel + " :" + message + "\r\n");
			writer.flush( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.irc("irc.freenode.net", mc.thePlayer.getName(), mc.thePlayer.getName(), "#dyarchy", 6667);
		this.connect();
	}
	       
	

}