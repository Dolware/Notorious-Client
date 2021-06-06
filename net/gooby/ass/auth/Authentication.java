package net.gooby.ass.auth;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;

public class Authentication {
	 public static String MakeJSONRequest(String username, String password){
	        JsonObject json1 = new JsonObject();
	        json1.addProperty("name", "Minecraft");
	        json1.addProperty("version", 1);
	        JsonObject json = new JsonObject();
	        
	        //json.addProperty("agent", json1.getAsNumber());
	        json.addProperty("username", username);
	        json.addProperty("password", password);

	        return json.toString();
	    }

	    public static String httpRequest(URL url, String content) throws Exception {
	        byte[] contentBytes = content.getBytes("UTF-8");

	        URLConnection connection = url.openConnection();
	        
	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Accept-Charset", "UTF-8");
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setRequestProperty("Content-Length", Integer.toString(contentBytes.length));

	        OutputStream requestStream = connection.getOutputStream();
	        requestStream.write(contentBytes, 0, contentBytes.length);
	        requestStream.close();

	        String response = "";
	        BufferedReader responseStream;
	        if (((HttpURLConnection) connection).getResponseCode() == 200) {
	            responseStream = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	        } else {
	            responseStream = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getErrorStream(), "UTF-8"));
	        }

	        response = responseStream.readLine();
	        responseStream.close();

	        if (((HttpURLConnection) connection).getResponseCode() != 200) {
	            //Failed to login (Invalid Credentials or whatever)
	        }

	        return response;
	    }
}
