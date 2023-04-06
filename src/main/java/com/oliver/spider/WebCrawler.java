package com.oliver.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.nodes.Document;

/****************************************************************************
 * <b>Title</b>WebCrawler.java<p/>
 * <b>Description: Starts the process for the web crawler</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/
public class WebCrawler {
	
	public static final String STARTING_URL = "https://smt-stage.qa.siliconmtn.com/";
	
	/**
	 * Main method that begins process
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		WebCrawler wc = new WebCrawler();
		wc.beginProcess();
	}
	
	/**
	 * Process method that calls different process blocks
	 * @throws IOException
	 */
	public void beginProcess() throws IOException {
		//writeCacheHTML();
		beginWebCrawl(STARTING_URL, null, null);
	}
	
	/**
	 * Writes cache to .txt file
	 */
	public void writeCacheHTML() {
		//GET cookies
		//POST cookies + access credentials
		//GET Cache Page HTML
		//Write HTML
	}
	
	/**
	 * Runs the web crawl
	 * @param currentURL
	 * @param visitedList
	 * @param unvisitedQueue
	 * @throws IOException
	 */
	public void beginWebCrawl(String currentURL, List<String> visitedList, Queue<String> unvisitedQueue) throws IOException {
		
		//Instantiate classes
		WebManager webManager = new WebManager();
		FileManager fileManager = new FileManager();
		URLManager urlManager = new URLManager(visitedList, unvisitedQueue);
		WebParser webParser = new WebParser();
		WebFilter webFilter = new WebFilter();
		
		//Receive current Document and URLQueue
		Document currentDoc = webManager.getPageAsDoc(currentURL);
		Queue<String> urlQueue = urlManager.getQueue();
		
		//Write Page HTML to .txt file
		fileManager.writePageHTML(currentDoc, currentURL);
		
		//Add Visited URL
		urlManager.addVisitedURL(currentURL);
		
		//Remove Queue Head
		urlManager.removeQueueHead();

		//Parse HTML for URL's, filter it for local URLs and repeats
		urlManager.addUnvisitedList(webFilter.filterRepeats(webFilter.getLocalURLs(webParser.parseHTML(currentDoc)), urlQueue));
		
		System.out.println("currentURL: " + currentURL);
		System.out.println("Visited List size: " + urlManager.getVisitedList().size() + " : " + urlManager.getVisitedList());
		System.out.println("URLQueue size: " + urlManager.getQueue().size() + " : " + urlManager.getQueue());
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
		
		//If the Queue isn't empty call beginWebCrawl again
		if (!urlQueue.isEmpty()) beginWebCrawl(urlQueue.peek(), urlManager.getVisitedList(), urlManager.getQueue());
	}
}
