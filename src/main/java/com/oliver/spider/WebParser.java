package com.oliver.spider;

import java.util.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/****************************************************************************
 * <b>Title</b>WebParser.java
 * <p/>
 * <b>Description: Parses web pages</b>
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

public class WebParser {

	/**
	 * Parse's HTML for all links
	 * 
	 * @param currentDoc
	 * @return list of links
	 */
	public List<String> parseHTML(Document currentDoc, String tag, String attribute) {
		Elements links = currentDoc.select(tag);
		List<String> linkList = new ArrayList<>();
		for (Element link : links) {
			linkList.add(link.attr(attribute).replaceFirst("https://", "").replace("#", ""));
		}
		return linkList;
	}
}
