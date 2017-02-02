package com.example.aggnimodule1.utilities;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joydeep on 28-03-2015.
 */
public class Utilities {

	public static String getLoginString(String username, String password) {
		JSONObject loginObject = new JSONObject();
		try {
			loginObject.put("MobileNumber", username);
			loginObject.put("Password", password);
		} catch (JSONException e) {

		}
		return loginObject.toString();
	}
	
	
	
	 public static void CopyStream(InputStream is, OutputStream os)
	    {
	        final int buffer_size=1024;
	        try
	        {
	            byte[] bytes=new byte[buffer_size];
	            for(;;)
	            {
	              int count=is.read(bytes, 0, buffer_size);
	              if(count==-1)
	                  break;
	              os.write(bytes, 0, count);
	            }
	        }
	        catch(Exception ex){}
	    }
}
