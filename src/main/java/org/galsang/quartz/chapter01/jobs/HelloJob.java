package org.galsang.quartz.chapter01.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作业类
 *
 * @author tengpeng.gao
 * @since 2018/8/21
 */
public class HelloJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Hello!  HelloJob is executing.");
    }

    public HelloJob() {
    }

}
