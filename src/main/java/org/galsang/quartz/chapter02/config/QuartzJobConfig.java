package org.galsang.quartz.chapter02.config;

import org.galsang.quartz.chapter02.jobs.MyJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 方法调用任务配置
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@Configuration
public class QuartzJobConfig {

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "myJobBean")
    public MethodInvokingJobDetailFactoryBean myJobBean(MyJob myJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-myJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(myJob); // 被执行的对象
        jobDetail.setTargetMethod("myJobBusinessMethod"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myJobTrigger")
    public CronTriggerFactoryBean myJobTrigger(@Qualifier("myJobBean") MethodInvokingJobDetailFactoryBean myJobBean) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(myJobBean.getObject());
        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        cronTriggerFactoryBean.setName("general-myJobTrigger");
        return cronTriggerFactoryBean;
    }

    @Bean("chapter02Scheduler")
    public Scheduler scheduler(@Qualifier("myJobTrigger") Trigger myJobTrigger, @Qualifier("myJobBean")
            MethodInvokingJobDetailFactoryBean myJobBean) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(myJobBean.getObject(), myJobTrigger);
        return scheduler;
    }

    /**
     * 调度器工厂Bean
     */
    @Bean(name = "chapter02SchedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("myJobTrigger") Trigger myJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        // 注册触发器
        bean.setTriggers(myJobTrigger);
        return bean;
    }

}
