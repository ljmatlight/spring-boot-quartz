package org.galsang;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 作业类
 *
 * @author tengpeng.gao
 * @since 2018/8/21
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println("Hello!  HelloJob is executing.");
    }

    public HelloJob() {
    }

}
