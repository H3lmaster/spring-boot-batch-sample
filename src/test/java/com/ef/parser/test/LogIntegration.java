package com.ef.parser.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.ef.parser.dao.LogDao;
import com.ef.parser.model.Log;

@RunWith(SpringRunner.class)
public class LogIntegration {

	@Autowired
	private LogDao logDao;
	
	@Test
	public void testLogQuery() {
		
		List<Log> logs = logDao.getLogsByStartDateDurationAndThreshold("2017-01-01.00:00:45", "daily", 10);
		assertThat(logs).isNotNull();
	}

}
