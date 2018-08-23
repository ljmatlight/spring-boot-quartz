package org.galsang.quartz;

import org.galsang.quartz.chapter01.Chapter01App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 简单任务调度
 *
 * @author tengpeng.gao
 * @since 2018/8/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Chapter01App.class)
@EnableAutoConfiguration
public class Chapter01AppTest {

    @Autowired
    private Scheduler chapter01Scheduler;

    @Test
    public void run() throws Exception {

        chapter01Scheduler.start();

        // 延迟 50秒，方便观察调度任务的执行情况
        Thread.sleep(50000);

    }


}