package com.oliver.spider;

import java.util.*;

/****************************************************************************
 * <b>Title</b>URLManager.java
 * <p/>
 * <b>Description: Manages URLs</b>
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

public class URLManager {

	// Declare urlQueue and visitedList
	private Queue<String> urlQueue = new LinkedList<>();
	private List<String> visitedList = new ArrayList<>();

	/**
	 * Constructor that initializes urlQueue and visitedList
	 * 
	 * @param currVisitedList
	 * @param currURLQueue
	 */
	public URLManager(List<String> currVisitedList, Queue<String> currURLQueue) {
		if (currVisitedList != null && currURLQueue != null) {
			setQueue(currURLQueue);
			addVisitedList(currVisitedList);
		}
	}

	// ~~~~~~~~~~~~~~~~~VISITED LIST~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Adds a URL to visitedList
	 * 
	 * @param visitedURL
	 */
	public void addVisitedURL(String visitedURL) {
		visitedList.add(visitedURL);
	}

	/**
	 * Adds a list of URL to visitedList
	 * 
	 * @param currVisitedList
	 */
	public void addVisitedList(List<String> currVisitedList) {
		visitedList.addAll(currVisitedList);
	}

	/**
	 * Get visitedList
	 * 
	 * @return visitedList
	 */
	public List<String> getVisitedList() {
		return visitedList;
	}

	// ~~~~~~~~~~~~~~~~~~~~QUEUE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Removes the head of urlQueue
	 */
	public void removeQueueHead() {
		if (!urlQueue.isEmpty())
			urlQueue.remove();
	}

	/**
	 * Get urlQueue
	 * 
	 * @return urlQueue
	 */
	public Queue<String> getQueue() {
		return urlQueue;
	}

	/**
	 * Adds a Queue to urlQueue
	 * 
	 * @param currQueue
	 */
	public void setQueue(Queue<String> currQueue) {
		urlQueue.addAll(currQueue);
	}

}
