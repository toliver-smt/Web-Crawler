package com.oliver.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	public Queue<String> unvisitedQueue = new LinkedList<>();
	public List<String> visitedList = new ArrayList<>();
	
	//Constructor
	public URLManager(List<String> visitedList, Queue<String> unvisitedQueue) {
		if (visitedList != null && unvisitedQueue != null) {
		setQueue(unvisitedQueue);
		addVisitedList(visitedList);
		}
	}
	
	//VISITED QUEUE
	//Contains URL's visited
	public void addVisitedURL(String visitedURL) {
		visitedList.add(visitedURL);
	}
	
	public void addVisitedList(List<String> myVisitedList) {
		visitedList.addAll(myVisitedList);
	}

	public List<String> getVisitedList() {
		return visitedList;
	}
	
	
	//QUEUE
	//Add URL to non-visited queue
	public void addUnvisitedURL(List<String> myList) {
		for (int i=0; i<myList.size(); i++) {
			//Check if URL is in visited list
			if (!visitedList.contains(myList.get(i))) {
				unvisitedQueue.add(myList.get(i));
			}
		}
	}
	
	public void removeQueueHead() {
		if (!unvisitedQueue.isEmpty()) {
			unvisitedQueue.remove();
		}
	}
	
	public Queue<String> getQueue() {
		return unvisitedQueue;
	}
	
	public void setQueue(Queue<String> myQueue) {
		unvisitedQueue.addAll(myQueue);
	}
	
	//Check if non-visited queue is empty

}
