package com.oliver.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public static void main(String[] args) throws IOException {
		WebCrawler wc = new WebCrawler();
		wc.beginProcess();
	}
	
	public void beginProcess() throws IOException {
		//Begin process to write Cache Page HTML
		//writeCacheHTML();
		//Begin web crawl from "smt-stage.qa.siliconmtn.com"
		beginWebCrawl("https://smt-stage.qa.siliconmtn.com/", null, null);
	}
	
	public void writeCacheHTML() {
		//GET cookies
		//POST cookies + access credentials
		//GET Cache Page HTML
		//Write HTML
	}

	public void beginWebCrawl(String myURL, List<String> visitedList, Queue<String> unvisitedQueue) throws IOException {
		
		/*System.out.println("myURL: " + myURL);
		System.out.println("visitedList: " + visitedList);
		System.out.println("unvisitedQueue: " + unvisitedQueue);
		System.out.println("~~~");
		*/
		
		//Instantiate classes
		FileManager fm = new FileManager();
		URLManager myURLManager = new URLManager(visitedList, unvisitedQueue);
		ParseHTML parser = new ParseHTML();
		FilterHTML filter = new FilterHTML();
		
		//Add Unvisited URL
		//myURLManager.addUnvisitedURL(myURL);
		
		//GET HTML and Write HTML
		fm.writePageHTML(myURL);
		
		//Add Visited URL
		myURLManager.addVisitedURL(myURL);
		
		//Remove Queue Head
		myURLManager.removeQueueHead();

		//Parse HTML for URL's, filter it for local URLs and repeats
		Queue<String> URLQueue = myURLManager.getQueue();
		myURLManager.addUnvisitedURL(filter.filterRepeats(filter.getLocalURLs(parser.parseHTML()), URLQueue));
		
		System.out.println("myURL: " + myURL);
		System.out.println("Visited List size: " + myURLManager.getVisitedList().size() + " : " + myURLManager.getVisitedList());
		System.out.println("URLQueue size: " + myURLManager.getQueue().size() + " : " + myURLManager.getQueue());
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
		
		if (!URLQueue.isEmpty()) beginWebCrawl(URLQueue.peek(), myURLManager.getVisitedList(), myURLManager.getQueue());
	}
}
