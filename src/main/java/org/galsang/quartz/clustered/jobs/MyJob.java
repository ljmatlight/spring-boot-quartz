package org.galsang.quartz.clustered.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 作业组件
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@Component
@EnableScheduling
@DisallowConcurrentExecution
public class MyJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.myJobBusinessMethod();
    }

    public void myJobBusinessMethod() {
        try {
            // 便于观察失火情况
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            logger.info("MyJob === 中断异常，{}", e);
        }
        this.logger.info("哇被触发了哈哈哈哈哈");
    }

}
