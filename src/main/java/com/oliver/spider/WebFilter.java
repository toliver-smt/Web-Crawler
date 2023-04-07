package com.oliver.spider;
import java.util.*;

/****************************************************************************
 * <b>Title</b>WebFilter.java<p/>
 * <b>Description: Filters web page's</b> 
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2023<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * 
 * @author Tyler Oliver
 * @version 1.0
 * @since April 4, 2023
 * <b>Changes: </b>
 ****************************************************************************/

public class WebFilter {
	
	/**
	 * Filter HTML for local URL's
	 * @param myList
	 * @return
	 */
	public List<String> getLocalURLs(List<String> myList) {
		myList.removeIf(s -> !s.startsWith("https"));
		return myList;
	}

	/**
	 * If an item in comparisonList isn't in myQueue, add it
	 * @param comparisonList
	 * @param myQueue
	 * @return
	 */
	public Queue<String> filterRepeats(List<String> comparisonList, Queue<String> myQueue) {
		Queue<String> newList = new LinkedList<>();
		for (String element : comparisonList) {
			if (!newList.contains(element) && !myQueue.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}
	
	/**
	 * If an item is in comparisonList and not in myQueue, remove it
	 * @param comparisonList
	 * @param myQueue
	 * @return
	 */
	public Queue<String> removeRepeatsFromQueue(List<String> comparisonList, Queue<String> myQueue) {
		myQueue.removeIf(s -> comparisonList.contains(s));
		return myQueue;
	}
}
