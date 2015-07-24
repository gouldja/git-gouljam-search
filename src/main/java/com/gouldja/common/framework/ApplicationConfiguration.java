package com.gouldja.common.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ApplicationConfiguration {
	
	public static final String PROPERTIES_PREFIX = "application";
    public static final String PROPERTIES_SUFFIX = "properties";
    public static final String PROPERTIES_FILE = PROPERTIES_PREFIX + "." + PROPERTIES_SUFFIX;
	
	private static Properties properties;	
	private static boolean    initialized;
	
	private static final Logger log;
	
	static {
		initialized = false;
		log = LogManager.getLogger(ApplicationConfiguration.class);
		properties = new Properties();
		addProperties(PROPERTIES_FILE);

	}	
	
	/**
	 * You can't instantiate this class, it's a singleton.
	 */
	private ApplicationConfiguration() {
	}
	
	/* 
	 * You can't clone it either.
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * This method provides a way of loading application properties at startup.
	 */
	public static void init() {
		if (initialized) {
			log.info("Application properties initialized successfully.");
		}
	}
	
	/**
	 * Add properties from a given system resource.
	 * @param resource Resource name.
	 */
	public static void addProperties(String resource) {		
		try {
			InputStream stream = FileHelper.getResourceInputStream(resource);
			properties.load(stream);
			log.info("Application properties added successfully from " + resource);
		}
		catch (Exception e) {
			log.error("Could not add properties from " + resource + " : " 
			    + StringHelper.NEW_LINE + e.toString());
		}
	}
    
    /**
     * Add regional properties from resource "application.<region>.properties".
     * @param region Regional identifier.
     */
    public static void addRegionalProperties(String region) {
        String resource = PROPERTIES_PREFIX + "." + region + "." + PROPERTIES_SUFFIX;
        try {
            addProperties(resource);
        }
        catch (Exception e) {
            log.warn("Could not add regional properties from " + resource + ".");
            log.warn(e);
        }
    }
	
	/**
	 * Get the named application property.
	 * @param key Key.
	 * @return The named property, or null if it does not exist.
	 */
	public static String getApplicationProperty(String key) {
		return properties.getProperty(key, null);
	}
	
	/**
	 * Get the named boolean application property. Note that missing values are
	 * assumed to be false.
	 * @param key Key.
	 * @return The named boolean property.
	 */
	public static Boolean getBooleanApplicationProperty(String key) {
		return new Boolean(properties.getProperty(key,"false"));
	}
	
	/**
	 * Get a list of values from an application property. The property must be in CSV format.
	 * Missing values will result in an empty list.
	 * @param key Key.
	 * @return A list of values.
	 */
	public static List getListApplicationProperty(String key) {
		return StringHelper.convertFromCSV(properties.getProperty(key,null));
	}
	
	/**
	 * Get all the current application properties.
	 * @return A clone of the current application properties.
	 */
	public static Properties getAllApplicationProperties() {
		return (Properties) properties.clone();
	}
	
	/**
	 * Get all application properties with a key that starts with the specified value.
	 * @param start  Start value.
	 * @param remove Remove the prefix supplied when generating keys for the map?
	 * @return A map of properties that match the given criteria.
	 */
	public static Map getApplicationPropertiesStartingWith(String start, boolean remove) {
		Map result = new HashMap();
		for (Iterator i = properties.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.startsWith(start)) {
				String value = properties.getProperty(key);
				if (remove) {
					String newKey = key.substring(start.length() + 1);
					result.put(newKey, value);
				}
				else {
				    result.put(key,value);
				}
			}
		}
		return result;
	}
        
    /**
     * List all current properties in a String.
     * @return String representation of current properties.
     */
    public static String listProperties() {
        StringBuffer sb = new StringBuffer("Application Properties");
        sb.append(StringHelper.NEW_LINE);
        for (Iterator i = properties.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            String value = (String) properties.get(key);
            sb.append(key + "=" + value + StringHelper.NEW_LINE);
        }
        return sb.toString();
    }
    
    /**
     * Is the specified property set to true or yes?
     * @param key Key
     * @return true = yes, false = no
     */
    public static boolean isApplicationPropertyTrue(String key) {
        String value = getApplicationProperty(key);
        return (value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")));       
    }

}
