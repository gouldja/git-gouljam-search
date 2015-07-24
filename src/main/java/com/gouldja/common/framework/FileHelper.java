package com.gouldja.common.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public class FileHelper {
    
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * Find a resource on the classpath (usually WEB-INF/classes) and open an InputStream.
	 * @param name Resource Name
	 * @return Input stream for that resource.
	 */
	public static InputStream getResourceInputStream(String name) throws AccessorException {	
		InputStream stream = null; 
		if (StringHelper.isNotEmpty(name)) {
		    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    if (classLoader != null) {
		    	stream = classLoader.getResourceAsStream(name);
		    }
		}
		if ( stream == null ) {
			throw new AccessorException("Resource " + name + " not found.",null);
		}
		return stream;
	}
	
	/**
	 * Get the contents of a resource on the classpath as a text String.
	 * @param name Resource Name
	 * @return Text contents of that resource.
	 * @throws IOException
	 */
	public static String getTextContentsOfResource(String name) throws AccessorException {
		List results = getContentsOfResource(name);
		return StringHelper.convertToFormattedString(results, StringHelper.TEXT_FORMAT);		
	}
	
	/**
	 * Get the contents of a resource on the classpath as a text String.
	 * @param name Resource Name
	 * @return Text contents of that resource.
	 * @throws IOException
	 */
	public static List getContentsOfResource(String name) throws AccessorException {	
		List results = null;
		InputStream stream = getResourceInputStream(name);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
		    results = getContents(reader);
		}
		catch (IOException ioe) {
			closeReaderOnError(reader);
			throw new AccessorException("Error reading resource " + name,ioe);
		}
		return results;
	}
	
	/**
	 * Get the contents of a file as a String. 
	 * NB : Use getTextContentsOfResource() if the file is on the classpath.
	 * @param fileName Fully qualified file name.
	 * @return Strings containing all lines from the file.
	 */
	public static String getTextContentsOfFile(String fileName) throws AccessorException {
		List results = getContentsOfFile(fileName);
		return StringHelper.convertToFormattedString(results, StringHelper.TEXT_FORMAT);
	}
	
	/**
	 * Get the contents of a file as a List of Strings. 
	 * NB : Use getTextContentsOfResource() if the file is on the classpath.
	 * @param fileName Fully qualified file name.
	 * @return List of Strings, each containing a line of the file.
	 */
	public static List getContentsOfFile(String fileName) throws AccessorException {
		List results = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			results = getContents(reader);
		}
		catch (IOException ioe) {
		    closeReaderOnError(reader);
			throw new AccessorException("Error reading file " + fileName,ioe); 
		}
		return results;
	}
	
	private static List getContents(BufferedReader reader) throws IOException {
		List results = new ArrayList();
		if (reader != null) {
		    String line = null;
			while ((line = reader.readLine()) != null) {
			    results.add(line);
		    }
			reader.close();
		}
		return results;
	}
	
	private static void closeReaderOnError(BufferedReader reader) {
		if (reader != null) {
		    try {
			    reader.close();
		    }
		    catch (IOException ioe) {
		    }
		}
	}
	

	/**
	 * Write text to a file.
	 * @param text Text.
	 * @param fileName File name.
	 * @throws AccessorException
	 */
	public static void writeTextToFile(String text, String fileName) throws AccessorException {
	    if (fileName != null) {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(fileName));
	            writer.write(text);
	            closeWriter(writer);
	        }
	        catch (IOException ioe) {
		        closeWriter(writer);
		        throw new AccessorException("Failed to write file.",ioe);
		    }
	    }
	    else {
	        throw new IllegalArgumentException("Cannot write file - no file name supplied.");
	    }
	}
	
	private static void closeWriter(BufferedWriter writer) {
		if (writer != null) {
		    try {
		        writer.flush();
			    writer.close();
		    }
		    catch (IOException ioe) {
		    }
		}
	}
	
}
