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
public class ParseHTML {
	public static final String LOGIN_URL = "https://smt-stage.qa.siliconmtn.com/";
	//Parse HTML for URL's
	public List<String> parseHTML() throws IOException {
		Document doc = Jsoup.connect(LOGIN_URL).get();
		Elements links = doc.select("a[href]");
		//Elements media = doc.select("[src]");
		//Elements imports = doc.select("link[href]");
		List<String> linkList = new ArrayList<>();
		
		/*for (Element src : media) {
			if (src.normalName().equals("img")) {
				linkList.add(src.attr("abs:src"));
				System.out.println(src.tagName() + ": " + src.attr("abs:src"));
			}
		}
		
		for (Element link : imports) {
			linkList.add(link.attr("abs:href"));
			System.out.println(link.tagName() + ": " + link.attr("abs:href"));
		}
		*/
		
		for (Element link : links) {
			linkList.add(link.attr("abs:href"));
		}

		return linkList;
	}
}
