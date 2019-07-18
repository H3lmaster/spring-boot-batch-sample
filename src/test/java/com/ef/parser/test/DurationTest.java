package com.ef.parser.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.ef.parser.utils.Duration;
import com.ef.parser.utils.Utils;

class DurationTest {

	@Test
	void dailyTest() {
		
		String startDateStr = "2017-01-01.00:00:00";
		
		// Get the end date and then compare it to the start date
		String endDateStr = Utils.addTimeToDateAsString(startDateStr, Duration.DAILY);
		
		//Get the diff between start date and end date
		Date startDate = Utils.parseDate(startDateStr);
		Date endDate = Utils.parseDate(endDateStr.replace(" ", "."));

		// Calculates the date diff
	    long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
	    // Confirms that the diff is equal to 1
	    assertThat(diff).isEqualTo(1);
	}
	
	@Test
	void hourlyTest() {
		
		String startDateStr = "2017-01-01.00:00:00";
		
		// Get the end date and then compare it to the start date
		String endDateStr = Utils.addTimeToDateAsString(startDateStr, Duration.HOURLY);
		
		//Get the diff between start date and end date
		Date startDate = Utils.parseDate(startDateStr);
		Date endDate = Utils.parseDate(endDateStr.replace(" ", "."));

		// Calculates the date diff
	    long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	    long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
	    // Confirms that the diff is equal to 1
	    assertThat(diff).isEqualTo(1);
	}

}
