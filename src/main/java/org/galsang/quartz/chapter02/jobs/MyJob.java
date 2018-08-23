package org.galsang.quartz.chapter02.jobs;

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
public class MyJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void myJobBusinessMethod() {
        this.logger.info("哇被触发了哈哈哈哈哈");
    }

}
