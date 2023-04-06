package com.oliver.spider;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;
import java.io.*;
import java.net.Socket;
/****************************************************************************
 * <b>Title</b>WebManager.java<p/>
 * <b>Description: Manages web page information</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/
public class WebManager {
	
	public static final String LOGIN_URL = "https://smt-stage.qa.siliconmtn.com";
	public static final int PORT_NUMBER = 443;
	public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
	
	public static Map<String, String> cookies = new HashMap<String, String>();
	
	public static void main(String[] args) throws IOException {
		WebManager wm = new WebManager();
		//wm.getPageHTML();
		wm.getCookies();
	}
	
	//GET Cookies
	public void getCookies() throws IOException {
		Connection.Response loginForm = Jsoup.connect(LOGIN_URL).method(Connection.Method.GET).execute();
		cookies.putAll(loginForm.cookies());
		System.out.println("Get Cookies");
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
	}
	
	//POST Cookies + access credentials
	public void login() {
		
	}
	
	//GET HTML
	/*public String getPageHTML() throws IOException {
		//Connect to login form url and retrieve the response
		Connection.Response loginForm = Jsoup.connect(LOGIN_URL).method(Connection.Method.GET).execute();
		//Document that contains the response html
		BufferedInputStream loginDoc = loginForm.bodyStream();
		System.out.println("loginDoc:");
		System.out.println(loginDoc);
		return loginDoc;
	}
	*/
}
