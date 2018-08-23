package org.galsang.quartz;

import org.galsang.quartz.chapter02.Chapter02App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 方法任务调度
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Chapter02App.class)
@EnableAutoConfiguration
public class Chapter02AppTest {

    @Autowired
    private SchedulerFactoryBean chapter02SchedulerFactory;

    @Test
    public void run() throws Exception {

        chapter02SchedulerFactory.getScheduler().start();

        // 延迟 50秒，方便观察调度任务的执行情况
        Thread.sleep(50000);

    }


}