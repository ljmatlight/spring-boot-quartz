package org.galsang.quartz.chapter01.config;

import org.galsang.quartz.chapter01.jobs.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 调度器配置信息
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@Configuration
public class QuartzChapter01Config {

    @Bean("chapter01JobDetail")
    public JobDetail jobDetail() {
        return newJob(HelloJob.class).withIdentity("job1", "group1").build();
    }

    @Bean("chapter01Trigger")
    public Trigger trigger() {
        return newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
    }

    @Bean("chapter01Scheduler")
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail(), trigger());
        return scheduler;
    }


}
