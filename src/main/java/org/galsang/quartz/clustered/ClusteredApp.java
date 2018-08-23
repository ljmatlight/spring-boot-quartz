package org.galsang.quartz.clustered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序
 *
 * @author tengpeng.gao
 * @since 2018/8/21
 */
@SpringBootApplication
public class ClusteredApp {

    public static void main(String[] args) {
        SpringApplication.run(ClusteredApp.class, args);
    }
}
