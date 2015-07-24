package com.gouldja.common.framework;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Useful date handling methods from standard patterns.
 */
public class FormatHelper {
	
	/**
	 * Extract a date from a string in a given format.
	 * @param date   Date string.
	 * @param format Format.
	 * @return Date.
	 */
	public static Date getDate(String date, String format) {
		Date result = null;
		if (StringHelper.isNotEmpty(date) && StringHelper.isNotEmpty(date)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.parse(date);
			}
			catch (ParseException pe) {}
		}
		return result;
	}
	
	/**
	 * Generate a date from a string, which can be in either long or short standard format.
	 * @param date Date string.
	 * @return Date.
	 */
	public static Date getDate(String date) {
		Date result = getShortDate(date);
		if (result == null) {
			result = getLongDate(date);
		}
		return result;
	}
	
	/**
	 * Generate a date from a string in short standard format.
	 * @param date Date string.
	 * @return Date.
	 */
	public static Date getShortDate(String shortDate) {
		String shortFormat = ApplicationConfiguration.getApplicationProperty("date.format.short");
		return getDate(shortDate,shortFormat);
	}
	
	/**
	 * Generate a date from a string in long standard format.
	 * @param date Date string.
	 * @return Date.
	 */
	public static Date getLongDate(String longDate) {
		String longFormat = ApplicationConfiguration.getApplicationProperty("date.format.long");
		return getDate(longDate,longFormat);
	}
	
	/**
	 * Generate a string from a date in a given format.
	 * @param date   Date.
	 * @param format Format.
	 * @return Date in string form.
	 */
	public static String formatDate(Date date, String format) {
		String result = null;
		if (date != null && StringHelper.isNotEmpty(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(date);
		}
		return result;
	}
	
	/**
	 * Generate a string in short standard format from a date.
	 * @param date Date
	 * @return Date in short standard string form.
	 */
	public static String formatShortDate(Date date) {
		String shortFormat = ApplicationConfiguration.getApplicationProperty("date.format.short");
        return formatDate(date,shortFormat);
	}

	/**
	 * Generate a string in short standard format from a date.
	 * @param date Date
	 * @return Date in short standard string form.
	 */
	public static String formatLongDate(Date date) {
		String longFormat = ApplicationConfiguration.getApplicationProperty("date.format.long");
        return formatDate(date,longFormat);
	}
	
	/**
	 * Format a number as currency.
	 * @param number Number.
	 * @return Currency format string.
	 */
	public static String formatCurrency(Number number) {
		String result = null;
		if (number != null) {
			String currency = ApplicationConfiguration.getApplicationProperty("currency.format");
			DecimalFormat formatter = new DecimalFormat(currency);
			result = formatter.format(number);
		}
		return result;
	}

}
