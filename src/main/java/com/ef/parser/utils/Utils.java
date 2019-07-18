package com.ef.parser.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author h.a.araujo.da.silva
 *
 */
public class Utils {
	
	public static final String DATE_FORMAT_INPUT = "yyyy-MM-dd.HH:mm:ss";
	public static final String DATE_FORMAT_SQL = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Adds the duration to the date(one hour or one day)
	 * and return a formatted date in the pattern yyyy-MM-dd.HH:mm:ss.
	 * @param dateStr
	 * @param duration
	 * @return
	 */
	public static String addTimeToDateAsString(String dateStr, Duration duration) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String startDateStr = dateStr;
		String endDateStr = null;
		Date startDate = null;
		Date endDate = null;
		
		try {
		
			// Get the start date from the command-line args
			startDate = new SimpleDateFormat(DATE_FORMAT_INPUT).parse(startDateStr);
		
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			
			if(Duration.DAILY.getDuration().equals(duration.getDuration())) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			} else {
				calendar.add(Calendar.HOUR_OF_DAY, 1);
			}
			
			// Sets and parse the endDate and startDate
			endDate = calendar.getTime();
			endDateStr = dateFormatter.format(endDate);
			startDateStr = dateFormatter.format(startDate);
		
		} catch (ParseException e) {
		}
		
		return endDateStr;
		
	}
	
	/**
	 * Formats a date using the pattern yyyy-MM-dd HH:mm:ss.
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_SQL);

		return dateFormatter.format(date);
	}
	
	/**
	 * Parses a date string in the pattern yyyy-MM-dd.HH:mm:ss.
	 * @param date
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_INPUT);
		Date result = null;
		
		try {
		
			result = dateFormatter.parse(dateStr);
		
		} catch (ParseException e) {
		}
		
		return result;
	}
	
}
