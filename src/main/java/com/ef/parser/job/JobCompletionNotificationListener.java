package com.ef.parser.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ef.parser.dao.LogDao;
import com.ef.parser.model.Log;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Autowired
    private Environment env;
	
	@Autowired
	private LogDao logDao;

	@Autowired
	public JobCompletionNotificationListener() {
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("Import job finished!");
			
			int threshold = Integer.parseInt(env.getProperty("threshold"));
			String startDateStr = env.getProperty("startDate");
			String duration = env.getProperty("duration");
			
			// Get the ips for the given params
			List<Log> logs = logDao.getLogsByStartDateDurationAndThreshold(startDateStr, duration, threshold);
			
			for(Log logEntry : logs) {
				log.info("IP Found <" + logEntry.getIp() + ">");
			}
			
			log.debug("CSV Path: " + env.getProperty("accesslog"));
			log.debug("Start Date: " + startDateStr);
			//log.debug("End Date: " + endDateStr);
			log.debug("Duration: " + env.getProperty("duration"));
			log.debug("Threshold: " + threshold);
		}
	}
	
}
