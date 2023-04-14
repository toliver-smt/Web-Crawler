package com.oliver.spider;

import java.util.*;

/****************************************************************************
 * <b>Title</b>WebFilter.java
 * <p/>
 * <b>Description: Filters web page's</b>
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

public class WebFilter {

	/**
	 * Filter HTML for local URL's
	 * 
	 * @param list
	 * @return updated list
	 */
	public List<String> getLocalURLs(List<String> list, String host) {
		list.removeIf(s -> !s.startsWith(host));
		return list;
	}

	/**
	 * If an item is in comparisonList and isn't in currQueue, add it
	 * 
	 * @param comparisonList
	 * @param currQueue
	 * @return updated Queue
	 */
	public Queue<String> filterRepeats(List<String> comparisonList, Queue<String> currQueue) {
		Queue<String> newQueue = new LinkedList<>();
		for (String element : comparisonList) {
			if (!newQueue.contains(element) && !currQueue.contains(element)) {
				newQueue.add(element);
			}
		}
		return newQueue;
	}

	/**
	 * If an item is in comparisonList and isn't in currQueue, remove it
	 * 
	 * @param comparisonList
	 * @param currQueue
	 * @return updateed Queue
	 */
	public Queue<String> removeRepeatsFromQueue(List<String> comparisonList, Queue<String> currQueue) {
		currQueue.removeIf(s -> comparisonList.contains(s));
		return currQueue;
	}
}
