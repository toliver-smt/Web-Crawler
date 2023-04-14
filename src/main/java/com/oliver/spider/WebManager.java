package com.oliver.spider;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/****************************************************************************
 * <b>Title</b>WebManager.java
 * <p/>
 * <b>Description: Manages web page information</b>
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

public class WebManager {

	/**
	 * Create a connection to currentURL
	 * 
	 * @param currentURL
	 * @return parsed Document of response body
	 */
	public Document getPageAsDoc(String currentURL, String host, int portNumber) {
		Document document = null;
		String path = currentURL.replace(host, "");
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, portNumber)) {

			// Create output stream
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			// Write GET request to output stream
			out.writeBytes("GET /" + path + " HTTP/1.1\r\n");
			out.writeBytes("Host: " + host + "\r\n");
			out.writeBytes("Connection: close\r\n\r\n");

			// Create InputStream and write contents to String
			String str = writeInput(socket.getInputStream());
			// Parse input for HTML
			String html = getHTMLFromString(str);
			// Convert HTML to Document
			document = Jsoup.parse(html, ("https://" + currentURL));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * POST cookies and formData to login to a website
	 * 
	 * @param requestURL
	 * @param cookies
	 * @param formData
	 */
	public String getValidCookies(String requestURL, String host, String formData) {
		String cookies = "";
		String path = requestURL.replace(host, "");
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, 443)) {

			// Create output stream
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			// write POST request to output stream
			out.writeBytes("POST " + path + " HTTP/1.1\r\n");
			out.writeBytes("Host: " + host + "\r\n");
			out.writeBytes("Content-Length: " + formData.length() + "\r\n");
			out.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
			out.writeBytes("Connection: close\r\n\r\n");
			out.writeBytes(formData + "\r\n");
			out.flush();

			// Create InputStream and write contents to String
			String str = writeInput(socket.getInputStream());
			// get cookies from response header
			cookies = getCookies(str);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cookies;
	}
	
	/**
	 * GET HTML Document from behind login
	 * 
	 * @param currentURL
	 * @param cookies
	 * @return parsed Document of response body
	 */
	public Document getPageBehindLogin(String currentURL, String host, String cookies) {
		Document document = null;
		String path = currentURL.replace(host, "");
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, 443)) {

			// Create output stream
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			// write GET request to output stream
			out.writeBytes("GET " + path + " HTTP/1.1\r\n");
			out.writeBytes("Host: " + host + "\r\n");
			out.writeBytes("Cookie: " + cookies + "\r\n");
			out.writeBytes("Connection: close\r\n\r\n");
			out.flush();

			// Create InputStream and write contents to String
			String str = writeInput(socket.getInputStream());
			// Parse input for HTML
			String html = getHTMLFromString(str);
			// Convert HTML to Document
			document = Jsoup.parse(html, ("https://" + currentURL));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * GET cookies from URL Only good for JSESSIONID, AWSALB, and AWSALBCORS
	 * 
	 * @param loginURL
	 * @return Map<String, String> of cookies
	 */
	public String getCookies(String responseHeader) {

		String[] firstSplit = responseHeader.split("JSESSIONID=");
		String[] secondSplit = firstSplit[1].split(";");
		String JSESSIONID = secondSplit[0];

		firstSplit = responseHeader.split("AWSALB=");
		secondSplit = firstSplit[1].split(";");
		String AWSALB = secondSplit[0];

		firstSplit = responseHeader.split("AWSALBCORS=");
		secondSplit = firstSplit[1].split(";");
		String AWSALBCORS = secondSplit[0];

		String cookies = "JSESSIONID=" + JSESSIONID + "; AWSALB=" + AWSALB + "; AWSALBCORS=" + AWSALBCORS + "";
		return cookies;
	}

	/**
	 * Declare and Initialize form Data
	 * 
	 * @param emailAddress
	 * @param password
	 * @return Form Data String
	 */
	public String initFormData(String emailAddress, String password) {
		return "requestType=reqBuild&pmid=ADMIN_LOGIN&emailAddress=" + emailAddress + "&password=" + password + "&l=";
	}

	/**
	 * Write contents from an InputStream to a String
	 * 
	 * @param inputStream
	 * @return read contents as a String
	 */
	public String writeInput(InputStream inputStream) {
		String writtenString = "";

		// Create InputStreamReader
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);) {
			BufferedReader reader = new BufferedReader(inputStreamReader);

			// Write BufferedReader to String
			String c = null;
			while ((c = reader.readLine()) != null) {
				writtenString += ((String) c);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return writtenString;
	}

	/**
	 * Get html from response
	 * 
	 * @param str
	 * @return html as a String
	 */
	public String getHTMLFromString(String str) {
		// Split String around <!DOCTYPE html>
		String[] strArr = str.split("<!DOCTYPE html>");
		// Select everything after <!DOCTYPE html>
		String html = "<!DOCTYPE html>" + strArr[1];
		return html;
	}

}
