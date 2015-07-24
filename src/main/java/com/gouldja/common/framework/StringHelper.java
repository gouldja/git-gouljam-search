package com.gouldja.common.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * Useful String Helper Methods.
 */
public class StringHelper {

	public static final String NEW_LINE      = System.getProperty("line.separator");
	public static final String CSV_DELIMITER = ",";
	public static final int    CSV_FORMAT    = 0;
	public static final int    TEXT_FORMAT   = 1;
	
	public static boolean isEmpty(String string) {
		return (string == null || string.length() == 0);
	}
	
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
	
	/**
	 * Get a string representation of the stack trace for a Throwable.
	 * @param t Throwable
	 * @return String representation of the stack trace.
	 */
	public static String getStackTrace(Throwable t) {
		String result = null;
		if (t != null) {
		    StringWriter sw = new StringWriter();
		    t.printStackTrace(new PrintWriter(sw));
		    result = sw.toString();
		}
		return result;
	}
	
	/**
	 * Generate a string representation that is CSV-compatible, e.g. with Excel.
	 * @param source Original string.
	 * @return CSV-compatible representation.
	 */
	public static String csvFormat(String source) {
	    String result = "";
	    if (source != null) {
		    result = source;
		    if (source.indexOf(',') > 0 || source.indexOf('"') > 0 || 
		        source.indexOf(StringHelper.NEW_LINE) > 0) {
			    String replaced = source.replaceAll("\"", "\"\"");
			    result = "\"" + replaced + "\"";
		    }
		}
		return result;
	}
	
	/**
	 * Generate a string representation of an object that is CSV-compatible, e.g. with Excel.
	 * @param o Object.
	 * @return CSV-compatible representation.
	 */
	public static String csvFormat(Object o) {
		return csvFormat(getValueOf(o));
	}
	
	/**
	 * Get the toString() value of an object, or an empty String if the object is null.
	 * Use this instead of String.valueOf(Object o) when you want the result to be "" 
	 * rather than "null" when the object is null.
	 * @param o Object.
	 * @return String representation.
	 */
	public static String getValueOf(Object o) {
		String result = null;
		if (o == null) {
			result = "";
		}
		else {
			result = String.valueOf(o);
		}
		return result;
	}
	
	/**
	 * Get a string representation of an object, using a standard application format where
	 * appropriate. Null objects are represented as an empty String.
	 * @param o Object.
	 * @return String representation.
	 */
	public static String getFormattedValueOf(Object o) {
		String result = null;
		if (o == null) {
			result = "";
		}
		else if (o instanceof String) {
			result = (String) o;
		}
		else if (o instanceof Date) {
			Date d = (Date) o;
			result = FormatHelper.formatShortDate(d);
		}
		else if (o instanceof Double) {
			Double d = (Double) o;
			result = FormatHelper.formatCurrency(d);
		}
		else {
			result = String.valueOf(o);
		}
		return result;
	}
	
	/**
	 * Generate a list of string values from a CSV format list.
	 * @param csvList CSV format list.
	 * @return List of individual string values.
	 */
	public static List<String> convertFromCSV(String csvList) {
		List<String> results = new ArrayList<String>();
		if (csvList != null) {
			StringTokenizer st = new StringTokenizer(csvList,CSV_DELIMITER);
			while (st.hasMoreTokens()) {
				results.add(st.nextToken().trim());
			}
		}
		return results;
	}
		
	/**
	 * Convert a List to a single String containing each a representation of each object, 
	 * separated by the supplied delimiter.
	 * @param list List.
	 * @param delimiter Delimiter.
	 * @return String format list.
	 */
	public static String convertToFormattedString(List list, int format) {
		if (format < CSV_FORMAT || format > TEXT_FORMAT) {
			throw new IllegalArgumentException("Format not supported.");
		}
		StringBuffer result = new StringBuffer();
		if (list != null) {
		    boolean first = true;
		    for (Iterator<String> i = list.iterator(); i.hasNext();) {
			    if (first) {
			    	first = false;
			    }
			    else if (format == CSV_FORMAT) {
				    result.append(CSV_DELIMITER);
			    }
			    else {
				    result.append(NEW_LINE);
			    }
			    String s = getFormattedValueOf(i.next());
			    if (format == CSV_FORMAT) {
				    s = csvFormat(s);
			    }
			    result.append(s);
		    }
		}
		return result.toString();
	}
	
	/**
	 * Convert a string containing a comma-separated list of key value pairs into a Map.
	 * @param keyValuePairs Key Value Pairs in a comma-separated list.
	 * @return Map.
	 */
	public static Map convertToMap(String keyValuePairs) {
		Map results = new HashMap();
		if (keyValuePairs != null) {
			List keyValues = convertFromCSV(keyValuePairs);
			for (Iterator i = keyValues.iterator(); i.hasNext();) {
				String keyValue = (String) i.next();
				int pos = keyValue.indexOf('=');
				if (pos > 0) {
					String key = keyValue.substring(0,pos).trim();
					String value = "";
					if (pos < (keyValue.length() - 1)) {
					    value = keyValue.substring(pos + 1).trim();
					}
					results.put(key, value);					
				}
			}
		}
		return results;
	}
	
	/**
	 * XOR each character in a String using a given character.
	 * @param input String to XOR.
	 * @param c character.
	 * @return XORed string.
	 */
	public static String xor(String input, char c) {	    
	    String result = input;
	    if (StringHelper.isNotEmpty(input)) {
	        char[] in = input.toCharArray();
	        char[] out = new char[in.length];
	        for (int i = 0; i < in.length; i++) {
	            out[i] = (char) (in[i] ^ c);
	        }
	        result = new String(out);
	    }
	    return result;
	}
    
    /**
     * Capitalize the first letter of a string.
     * @param input Input
     * @return Capitalized result
     */
    public static String initCap(String input) {
        String result = input;
        if (input != null && input.length() > 0) {
            if (input.length() > 1) {
                result = input.substring(0,1).toUpperCase() + input.substring(1);
            }
            else {
                result = input.toUpperCase();
            }
        }
        return result;
    }
    
    /**
     * Capitalize each word in a string.
     * @param input Input
     * @return Capitalized result
     */
    public static String capitalize(String input) {
        String result = input;
        if (input != null && input.length() > 0) {
            StringTokenizer st = new StringTokenizer(input," ");
            StringBuffer sb = new StringBuffer();
            while (st.hasMoreTokens()) {
                sb.append(initCap(st.nextToken()));
                if (st.hasMoreTokens()) {
                    sb.append(" ");
                }
            }
            result = sb.toString();
        }
        return result;
    }
    
    /**
     * Generate a list of fields from a CSV line. This method ignores commas
     * inside quotes and converts pairs of double quotes.
     * @param csvLine CSV Line.
     * @return List of fields.
     */
    public static List getFieldsFromCSV(String csvLine) {
        List list = new ArrayList();
        StringBuffer field = new StringBuffer();
        boolean insideQuotes = false;
        if (csvLine != null) {
            for (int i = 0; i < csvLine.length(); i++) {
                
            //  Get this character and the next one.
              
                char current = csvLine.charAt(i);
                char next = 0;
                if (i < (csvLine.length() - 1)) {
                    next = csvLine.charAt(i + 1);
                }
                
            //  Handle a double quote.
                
                if (current == '"') {
                    if (insideQuotes) {
                        if (next == '"') {
                            field.append(current);
                            i++;
                        }
                        else {
                            insideQuotes = false;
                        }
                    }
                    else {
                        insideQuotes = true;
                    }
                }
                
            //  Handle a comma.
                
                else if (current == ',') {
                    if (insideQuotes) {
                        field.append(current);
                    }
                    else {
                        list.add(field.toString());
                        field = new StringBuffer();
                    }
                }
            
            //  All other characters get appended to the current field.
                
                else {
                    field.append(current);
                }
                
            }
            list.add(field.toString());
        }
        return list;
    }

    /**
     * Alternative to String.replaceAll() and String.replace().
     * @param source   Source string
     * @param original original character
     * @param replace  replaced by
     * @return String with the specified character replaced by the specified string.
     */
    public static String replaceAll(String source, char original, String replace) {
        StringBuffer result = new StringBuffer();
        if (source != null) {
            for (int i = 0; i < source.length(); i++) {
                char c = source.charAt(i);
                if (c == original) {
                    result.append(replace);
                }
                else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
	

    /**
     * This method can cope with multi-line CSV-values. It parses the data 
     * of a CSV file as a whole and returns a list of list where the former
     * represents the lines of the file and the latter the values per line.
     * This method ignores commas inside quotes and converts pairs of 
     * double quotes.
     * @param csvData CSV Data.
     * @return List of value-Lists
     */
    public static List parseCSVData(String csvData) {
    	List lines = new ArrayList();
        List values = new ArrayList();
        StringBuffer field = new StringBuffer();
        boolean insideQuotes = false;
        
        if (csvData != null) {
            for (int i = 0; i < csvData.length(); i++) {
                
            //  Get this character and the next one.
                char current = csvData.charAt(i);
                char next = 0;
                if (i < (csvData.length() - 1)) {
                    next = csvData.charAt(i + 1);
                }
                
            //  Handle a double quote.
                if (current == '"') {
                    if (insideQuotes) {
                        if (next == '"') {
                            field.append(current);
                            i++;
                        } else {
                            insideQuotes = false;
                        }
                    } else {
                        insideQuotes = true;
                    }
                }
                
            //  Handle a comma.
                else if (current == ',') {
                    if (insideQuotes) {
                        field.append(current);
                    } else {
                        values.add(field.toString());
                        field = new StringBuffer();
                    }
                }
            
            //  Handle multi-line values.
                else if (current == '\n' && !insideQuotes) {
                    if (field.length() > 0) {
                    	values.add(field.toString());
                    }
                	if (values.size() > 0) {
                		lines.add(values);
                	}
                	values = new ArrayList();
                	field = new StringBuffer();
                }
                
            //  All other characters get appended to the current field.
                
                else {
                    field.append(current);
                }
            }
           
            // Avoid appending empty lines 
            if (field.length() > 0) {
            	values.add(field.toString());
            }
        	if (values.size() > 0) {
        		lines.add(values);
        	}
        }
        return lines;
    }
    
    /**
     * Generate a random string.
     * @return Random string
     */
    public static String getRandomString() {
        Random r = new Random();
        return Long.toString(Math.abs(r.nextLong()),36);
    }
    
    /**
     * Are these two Strings the same? This method allows either String to be null and returns
     * true if BOTH Strings are null.
     * @param s1 First String
     * @param s2 Second String
     * @return true = same or both null, false = different
     */
    public static boolean matches(String s1, String s2) {
        boolean matches = false;
        if (StringHelper.isEmpty(s1)) {
            if (StringHelper.isEmpty(s2)) {
                matches = true;
            }
        }
        else {
            if (s1.equals(s2)) {
                matches = true;
            }
        }
        return matches;
    }
    
    /**
     * Convert a map to a multiline string.
     * @param map Map
     * @return Multiline String
     */
    public static String mapToString(Map map) {
        ToStringBuilder builder = new ToStringBuilder(map,ToStringStyle.MULTI_LINE_STYLE);
        TreeSet keys = new TreeSet(map.keySet());
        for (Iterator i = keys.iterator(); i.hasNext();) {
            Object key = i.next();
            Object value = map.get(key);
            builder.append(getFormattedValueOf(key),value);
        }
        return builder.toString();
    }
    
    /**
     * Create an object from a String.
     * This method currently supports String, Long, Integer, Boolean, Date and Double conversion.
     * Attempts to create any other object type will result in an IllegalArgumentException being
     * thrown.
     * @param value    String Value
     * @param objClass Object Type
     * @return new Object, or null if the String is null or empty.
     */
    public static Object createObject(String value, Class objClass) {
        Object result = null;
        if (StringHelper.isNotEmpty(value) && objClass != null) {
            if (objClass.equals(String.class)) {
                result = value;
            }
            else if (objClass.equals(Long.class)) {
                result = new Long(value);
            }
            else if (objClass.equals(Integer.class)) {
                result = new Integer(value);
            }
            else if (objClass.equals(Boolean.class)) {
                result = new Boolean(value);
            }
            else if (objClass.equals(Date.class)) {
                try {
                    result = FormatHelper.getLongDate(value);
                }
                catch (Exception e) {
                    result = FormatHelper.getShortDate(value);
                }
            }
            else if (objClass.equals(Double.class)) {
                result = new Double(value);
            }
            else {
                throw new IllegalArgumentException(
                    objClass.getName() + " is not supported by this method.");
            }
        }
        return result;
    }
    
}
