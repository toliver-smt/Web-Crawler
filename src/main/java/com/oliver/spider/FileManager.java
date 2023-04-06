package com.oliver.spider;

import java.io.*;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.*;

/****************************************************************************
 * <b>Title</b>FileManager.java<p/>
 * <b>Description: Manages files</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/

public class FileManager {
	public static final String LOGIN_URL = "https://smt-stage.qa.siliconmtn.com";
	
	//write HTML to file
	public void writePageHTML(String loginURL) throws IOException {
		//Connect to login form url and retrieve the response
		Connection.Response loginForm = Jsoup.connect(loginURL).method(Connection.Method.GET).execute();
		//Document that contains the response html
		InputStream inputStream = new ByteArrayInputStream(loginForm.parse().toString().getBytes());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		
		File outputFile = new File("src/main/java/com/oliver/spider/" + loginURL.replaceAll("https://smt-stage.qa.siliconmtn.com/", "") + ".txt");
    	FileOutputStream writer = new FileOutputStream(outputFile);
		
    	//while loop that prints each line read
    	int inData;
    	while((inData = in.read()) != -1) {
    		writer.write(inData);
    	}
	}
}
