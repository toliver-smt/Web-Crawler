package com.oliver.spider;

import java.io.IOException;
import java.util.*;

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
		writeCacheHTML();
		crawl(STARTING_URL, null, null);
	}
	
	/**
	 * Writes cache to .txt file
	 */
	public void writeCacheHTML() throws IOException {
		
		//Instantiate classes
		WebManager webManager = new WebManager();
		FileManager fileManager = new FileManager();
		
		webManager.getCookies();
		webManager.initFormData();
		webManager.initHeader();
		webManager.login();
		fileManager.writePageHTML(webManager.getPageBehindLogin());
	}
	
	/**
	 * Runs the web crawl
	 * @param currentURL
	 * @param visitedList
	 * @param unvisitedQueue
	 * @throws IOException
	 */
	public void crawl(String currentURL, List<String> visitedList, Queue<String> unvisitedQueue) throws IOException {
		
		//Instantiate classes
		WebManager webManager = new WebManager();
		FileManager fileManager = new FileManager();
		URLManager urlManager = new URLManager(visitedList, unvisitedQueue);
		WebParser webParser = new WebParser();
		WebFilter webFilter = new WebFilter();
		
		//Receive current Document and totalQueue
		Document currentDoc = webManager.getPageAsDoc(currentURL);
		Queue<String> totalQueue = urlManager.getQueue();
		List<String> currVisitedList = urlManager.getVisitedList();
		
		fileManager.writePageHTML(currentDoc);
		urlManager.addVisitedURL(currentURL);
		urlManager.removeQueueHead();
		
		//Parse the HTML from Document
		List<String> parsedHTML = webParser.parseHTML(currentDoc);
		//GET all URL's that are local
		List<String> unfilteredLinkList = webFilter.getLocalURLs(parsedHTML);
		//If item in currURLsList isn't in totalQueue, add it
		Queue<String> newTotalQueue = webFilter.filterRepeats(unfilteredLinkList, totalQueue);
		//If item in currVisitedList is in newTotalQueue, remove it
		Queue<String> filteredQueue = webFilter.removeRepeatsFromQueue(currVisitedList, newTotalQueue);
		//Add filtered Queue to totalQueue;
		urlManager.addUnvisitedList(filteredQueue);
		
		System.out.println("currentURL: " + currentURL);
		System.out.println("Visited List size: " + currVisitedList.size() + " : " + currVisitedList);
		System.out.println("URLQueue size: " + totalQueue.size() + " : " + totalQueue);
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
		
		//If the Queue isn't empty call beginWebCrawl again
		if (!totalQueue.isEmpty()) crawl(totalQueue.peek(), currVisitedList, totalQueue);
	}
}
