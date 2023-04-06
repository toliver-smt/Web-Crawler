package com.oliver.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/****************************************************************************
 * <b>Title</b>ParseHTML.java<p/>
 * <b>Description: Parse's HTML</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/
public class WebParser {

	/**
	 * Parse's HTML for all links
	 * @param currentDoc
	 * @return
	 * @throws IOException
	 */
	public List<String> parseHTML(Document currentDoc) throws IOException {
		Elements links = currentDoc.select("a[href]");
		List<String> linkList = new ArrayList<>();
		for (Element link : links) linkList.add(link.attr("abs:href"));
		return linkList;
	}
}
