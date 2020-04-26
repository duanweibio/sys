package com.schedul;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务
 * @author dwb
 *
 */
public class WeatherDataSyncJob extends QuartzJobBean {

	private static final Logger logger =LoggerFactory.getLogger(WeatherDataSyncJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("Weather Data Sync Job Start!");
		System.out.println("==================Hello World!================");
		logger.info("Weather Data Sync Job End");
	}

}
