package com.oliver.spider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.jsoup.nodes.Document;

/****************************************************************************
 * <b>Title</b>WebCrawler.java
 * <p/>
 * <b>Description: Starts the process for the web crawler</b>
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
public class WebCrawler {

	private String HOST;
	private String REQUEST_URL;
	private String CACHE_STATS;
	private String EMAIL_ADDRESS;
	private String PASSWORD;
	private String PARSE_TAG;
	private String PARSE_ATTRIBUTE;

	/**
	 * Main method that begins process
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler();
		wc.beginProcess();
	}

	/**
	 * Process method that calls different process blocks
	 */
	public void beginProcess() {
		initConstants();
		writeCacheHTML();
		crawl(HOST, null, null);
	}

	/**
	 * Initialize constants
	 */
	public void initConstants() {
		String configFilePath = "src/main/resources/config/config.properties";
		try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
			Properties prop = new Properties();
			prop.load(propsInput);
			HOST = prop.getProperty("HOST");
			REQUEST_URL = prop.getProperty("REQUEST_URL");
			CACHE_STATS = prop.getProperty("CACHE_STATS");
			EMAIL_ADDRESS = prop.getProperty("EMAIL_ADDRESS");
			PASSWORD = prop.getProperty("PASSWORD");
			PARSE_TAG = prop.getProperty("PARSE_TAG");
			PARSE_ATTRIBUTE = prop.getProperty("PARSE_ATTRIBUTE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes cache to a text file
	 */
	public void writeCacheHTML() {
		WebManager webManager = new WebManager();
		FileManager fileManager = new FileManager();
		String formData = webManager.initFormData(EMAIL_ADDRESS, PASSWORD);
		String cookies = webManager.getValidCookies(REQUEST_URL, HOST, formData);
		Document thisDoc = webManager.getPageBehindLogin(CACHE_STATS, HOST, cookies);
		fileManager.writePageHTML(thisDoc, CACHE_STATS);
	}

	/**
	 * Runs the web crawl
	 * 
	 * @param currentURL
	 * @param visitedList
	 * @param unvisitedQueue
	 */
	public void crawl(String currentURL, List<String> visitedList, Queue<String> unvisitedQueue) {
		WebManager webManager = new WebManager();
		FileManager fileManager = new FileManager();
		URLManager urlManager = new URLManager(visitedList, unvisitedQueue);
		WebParser webParser = new WebParser();
		WebFilter webFilter = new WebFilter();

		// Receive current Document, totalQueue, and totalVisitedList
		Document currentDoc = webManager.getPageAsDoc(currentURL, HOST, 443);
		Queue<String> totalQueue = urlManager.getQueue();
		List<String> totalVisitedList = urlManager.getVisitedList();
		
		// Write the currentDoc to a file
		fileManager.writePageHTML(currentDoc, currentURL);
		// Add the currentURL to totalVisitedList
		urlManager.addVisitedURL(currentURL);
		// Remove Queue head, i.e. currentURL after first iteration
		urlManager.removeQueueHead();
		
		
		// Parse the HTML from currentDoc
		List<String> parsedHTML = webParser.parseHTML(currentDoc, PARSE_TAG, PARSE_ATTRIBUTE);
		// Get all URLs that are local
		List<String> localURLs = webFilter.getLocalURLs(parsedHTML, currentURL);
		// If an item is in localURLs and isn't in totalQueue, add it to totalQueue
		Queue<String> newTotalQueue = webFilter.filterRepeats(localURLs, totalQueue);
		// If an item is in totalVisitedList and isn't in newTotalQueue, remove it from newTotalQueue
		Queue<String> filteredQueue = webFilter.removeRepeatsFromQueue(totalVisitedList, newTotalQueue);
		// Add filteredQueue to totalQueue;
		urlManager.setQueue(filteredQueue);

		System.out.println("currentURL: " + currentURL);
		System.out.println("Visited List size: " + totalVisitedList.size() + " : " + totalVisitedList);
		System.out.println("URLQueue size: " + totalQueue.size() + " : " + totalQueue);
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
		
		// If totalQeue isn't empty call Crawl again
		if (!totalQueue.isEmpty())
			crawl(totalQueue.peek(), totalVisitedList, totalQueue);
	}
}
