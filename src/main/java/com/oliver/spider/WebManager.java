package com.oliver.spider;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.*;
import java.io.*;

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
	
	public static final String LOGIN_URL = "https://smt-stage.qa.siliconmtn.com/admintool";
	public static final String REQUEST_URL = "https://smt-stage.qa.siliconmtn.com/sb/admintool";
	public static final String CACHE_STATS = "https://smt-stage.qa.siliconmtn.com/admintool?cPage=stats&actionId=FLUSH_CACHE";
	public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
	public static final String EMAIL_ADDRESS = "tyler.oliver@siliconmtn.com";
	public static final String PASSWORD = "Anchovy^3ggs";
	
	public static Map<String, String> cookies = new HashMap<>();
	public static Map<String, String> formData = new HashMap<>();
	public static Map<String, String> headers = new HashMap<>();
	
	/**
	 * Create a connection to currentURL
	 * @param currentURL
	 * @return
	 * @throws IOException
	 */
	public Document getPageAsDoc(String currentURL) throws IOException {
		return Jsoup.connect(currentURL).get();
	}
	
	/**
	 * GET cookies and put them in a map
	 * @throws IOException
	 */
	public void getCookies() throws IOException {
		Connection.Response loginForm = Jsoup.connect(LOGIN_URL)
				.method(Connection.Method.GET)
				.userAgent(USER_AGENT)
				.execute();
	
		cookies.putAll(loginForm.cookies());
	}
	
	public void initFormData() {
		formData.put("requestType", "reqBuild");
		formData.put("pmid", "ADMIN_LOGIN");
        formData.put("emailAddress", EMAIL_ADDRESS);
        formData.put("password", PASSWORD);
        formData.put("l", "");
	}
	
	public void initHeader() {
        headers.put("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
        headers.put("origin", "https://smt-stage.qa.siliconmtn.com");
        headers.put("referer", "https://smt-stage.qa.siliconmtn.com/admintool");
	}
	
	/**
	 * POST Cookies + access credentials
	 * @throws IOException
	 */
	public void login() throws IOException {
        Jsoup.connect(REQUEST_URL)
        	.cookies(cookies)
        	.data(formData)
        	.method(Connection.Method.POST)
        	.headers(headers)
        	.execute();
	}
	
	/**
	 * GET the cache page
	 * @throws IOException
	 */
	public Document getPageBehindLogin() throws IOException {
		Connection.Response flushCache = Jsoup.connect(CACHE_STATS)
	        	.cookies(cookies)
	        	.data(formData)
				.method(Connection.Method.GET)
				.headers(headers)
				.execute();
		
		Document myDoc = flushCache.parse();
		System.out.println(myDoc);
		return myDoc;
	}
}
