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
	
	/**
	 * Write currentDoc to .txt file
	 * @param currentDoc
	 * @param currentURL
	 * @throws IOException
	 */
	public void writePageHTML(Document currentDoc, String currentURL) throws IOException {
		
		//Create buffered input stream
		InputStream inputStream = new ByteArrayInputStream(currentDoc.toString().getBytes());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		
		//Create output stream
		File outputFile = new File("src/main/resources/res/" + currentURL.replaceAll("https://smt-stage.qa.siliconmtn.com/", "") + ".txt");
    	FileOutputStream writer = new FileOutputStream(outputFile);
		
    	//Write to file
    	int inData;
    	while((inData = in.read()) != -1) {
    		writer.write(inData);
    	}
	}
}
