package com.ef.parser.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ef.parser.model.Log;
import com.ef.parser.utils.Duration;
import com.ef.parser.utils.Utils;

@Component
public class LogDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public LogDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * Get the logs using the filters passed as param.
	 * @param startDateSrt
	 * @param duration
	 * @param threshold
	 * @return
	 */
	public List<Log> getLogsByStartDateDurationAndThreshold(String startDateStr, String duration, int threshold){
		
		List<Log> logs = new ArrayList<Log>();
		
		String endDateStr = null;
		
		// Sets and parse the endDate and startDate
		endDateStr = Utils.addTimeToDateAsString(startDateStr, Duration.getByName(duration));
		startDateStr = Utils.formatDate(Utils.parseDate(startDateStr));
					
		
		jdbcTemplate.query("SELECT ip, count(1) FROM log"
				+ " WHERE date between '" + startDateStr 
				+ "' AND '" + endDateStr
				+ "' group by ip "
				+ " having count(1) > " + threshold,
			(rs, row) -> new Log(null,
				rs.getString(1), null, null, null)
		).forEach(logEntry -> logs.add(logEntry));
		
		
		return logs;
	}
	
}
