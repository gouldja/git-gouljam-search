package com.gouldja.common.framework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    
	  try {
		  	InputStream is = new URL(url).openStream();
	  
   
    	
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      is.close();
      return json;
    } catch (FileNotFoundException e) {return null;
    } 
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("https://graph.facebook.com/19292868552");
    System.out.println(json.toString());
    System.out.println(json.get("id"));
  }
}