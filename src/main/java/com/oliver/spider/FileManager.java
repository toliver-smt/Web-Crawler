package com.oliver.spider;
import java.io.*;
import org.jsoup.nodes.Document;

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
	 * @throws IOException
	 */
	public void writePageHTML(Document currentDoc) throws IOException {
		
		//Define the title of the Document
		String title = currentDoc.title();
		
		//Create buffered input stream
		InputStream inputStream = new ByteArrayInputStream(currentDoc.toString().getBytes());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		
		//Create output stream
		File outputFile = new File("src/main/resources/res/" + title + ".txt");
    	FileOutputStream writer = new FileOutputStream(outputFile);
		
    	//Write to file
    	int inData;
    	while((inData = in.read()) != -1) {
    		writer.write(inData);
    	}
    	
    	//Close streams
    	in.close();
    	writer.close();
	}
}
