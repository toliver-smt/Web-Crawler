package com.oliver.spider;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.*;
import java.io.*;

/****************************************************************************
 * <b>Title</b>WebManager.java
 * <p/>
 * <b>Description: Manages web page information</b>
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023
 * <p/>
 * <b>Company:</b> Silicon Mountain Technologies
 * <p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023 <b>Changes: </b>
 ****************************************************************************/

public class WebManager {

	/**
	 * Create a connection to currentURL
	 * 
	 * @param currentURL
	 * @return parsed Document of response body
	 */
	public Document getPageAsDoc(String currentURL) {
		Document document = null;
		try {
			document = Jsoup.connect(currentURL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * GET cookies from URL
	 * 
	 * @param loginURL
	 * @return Map<String, String> of cookies
	 */
	public Map<String, String> getCookies(String loginURL) {
		Map<String, String> cookies = null;
		try {
			cookies = Jsoup.connect(loginURL).method(Connection.Method.GET).execute().cookies();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cookies;
	}

	/**
	 * Declare and Initialize form Data
	 * 
	 * @param emailAddress
	 * @param password
	 * @return Form Data Map
	 */
	public Map<String, String> initFormData(String emailAddress, String password) {
		Map<String, String> formData = new HashMap<>();
		formData.put("requestType", "reqBuild");
		formData.put("pmid", "ADMIN_LOGIN");
		formData.put("emailAddress", emailAddress);
		formData.put("password", password);
		return formData;
	}

	/**
	 * POST cookies and formData to login to a website
	 * 
	 * @param requestURL
	 * @param cookies
	 * @param formData
	 */
	public void login(String requestURL, Map<String, String> cookies, Map<String, String> formData) {
		try {
			Jsoup.connect(requestURL).cookies(cookies).data(formData).method(Connection.Method.POST).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET HTML Document from behind login
	 * 
	 * @param currentURL
	 * @param cookies
	 * @param formData
	 * @return parsed Document of response body
	 */
	public Document getPageBehindLogin(String currentURL, Map<String, String> cookies, Map<String, String> formData) {
		Document document = null;
		try {
			document = Jsoup.connect(currentURL).cookies(cookies).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(document);
		return document;
	}
}
