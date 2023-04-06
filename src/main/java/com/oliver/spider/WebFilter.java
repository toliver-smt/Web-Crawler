package com.oliver.spider;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;

/****************************************************************************
 * <b>Title</b>FilterHTML.java<p/>
 * <b>Description: Filters the HTML</b> 
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
	
	//Could be split up into two methods
	/**
	 * Filter HTML for repeats within itself and check its not in visitedList
	 * @param currentList
	 * @param totalQueue
	 * @return
	 */
	public List<String> filterRepeats(List<String> currentList, Queue<String> totalQueue) {
		List<String> newList = new ArrayList<>();
		for (int i=0; i<currentList.size(); i++) {
			if (!newList.contains(currentList.get(i)) && !totalQueue.contains(currentList.get(i))) {
				newList.add(currentList.get(i));
			}
		}
		return newList;
	}
}
