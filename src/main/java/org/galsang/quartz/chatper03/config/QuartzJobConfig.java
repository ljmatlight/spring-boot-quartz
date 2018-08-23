package org.galsang.quartz.chatper03.config;

import org.galsang.quartz.chatper03.jobs.MyJob;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 简单的持久化任务配置
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@Configuration
public class QuartzJobConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "chatper03JobBean")
    public JobDetailFactoryBean chatper03JobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-chatper03Job"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(MyJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "chatper03JobTrigger")
    public CronTriggerFactoryBean chatper03JobTrigger(@Qualifier("chatper03JobBean") JobDetailFactoryBean chatper03JobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(chatper03JobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-chatper03JobTrigger");
        return tigger;
    }

    @Bean("chapter03Scheduler")
    public SchedulerFactoryBean schedulerFactory(DataSource dataSource, Trigger... triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.jdbc.properties"));
        } catch (IOException e) {
            this.logger.error("加载quartz.properties失败", e);
            throw new Error(e);
        }
        bean.setQuartzProperties(p);

        /* 使用应用的数据源 */
        bean.setDataSource(dataSource);

        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(10);
        // 注册触发器
        bean.setTriggers(triggers);
        return bean;
    }

}
