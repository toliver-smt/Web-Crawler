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
public class FilterHTML {
	//Filter HTML for local URL's
	public List<String> getLocalURLs(List<String> myList) {
		myList.removeIf(s -> !s.startsWith("https"));
		return myList;
	}
	
	//Filter HTML for repeats
	public List<String> filterRepeats(List<String> myList, Queue<String> myList2) {
		List<String> newList = new ArrayList<>();
		for (int i=0; i<myList.size(); i++) {
			if (!newList.contains(myList.get(i)) && !myList2.contains(myList.get(i))) {
				newList.add(myList.get(i));
			}
		}
		//System.out.println("myList");
		//System.out.println(myList);
		return newList;
	}
}
