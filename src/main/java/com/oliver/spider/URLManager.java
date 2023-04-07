package com.oliver.spider;
import java.util.*;

/****************************************************************************
 * <b>Title</b>URLManager.java<p/>
 * <b>Description: Manages URL's</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/

public class URLManager {
	
	//Declare urlQueue and visitedList
	public Queue<String> urlQueue = new LinkedList<>();
	public List<String> visitedList = new ArrayList<>();
	
	/**
	 * Constructor that initializes urlQueue and visitedList
	 * @param myVisitedList
	 * @param myQueue
	 */
	public URLManager(List<String> myVisitedList, Queue<String> myQueue) {
		if (myVisitedList != null && myQueue != null) {
		setQueue(myQueue);
		addVisitedList(myVisitedList);
		}
	}
	
	//~~~~~~~~~~~~~~~~~VISITED LIST~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Adds a URL to visitedList
	 * @param visitedURL
	 */
	public void addVisitedURL(String visitedURL) {
		visitedList.add(visitedURL);
	}
	
	/**
	 * Adds a list of URL to visitedList
	 * @param myVisitedList
	 */
	public void addVisitedList(List<String> myVisitedList) {
		visitedList.addAll(myVisitedList);
	}
	
	/**
	 * Get visitedList
	 * @return
	 */
	public List<String> getVisitedList() {
		return visitedList;
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~QUEUE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Adds a list to urlQueue
	 * @param myList
	 */
	public void addUnvisitedList(Queue<String> myList) {
		urlQueue.addAll(myList);
	}
	
	/**
	 * Removes the head of urlQueue
	 */
	public void removeQueueHead() {
		if (!urlQueue.isEmpty()) urlQueue.remove();
	}
	
	/**
	 * Get urlQueue
	 * @return
	 */
	public Queue<String> getQueue() {
		return urlQueue;
	}
	
	/**
	 * Adds a Queue to urlQueue
	 * @param myQueue
	 */
	public void setQueue(Queue<String> myQueue) {
		urlQueue.addAll(myQueue);
	}

}
