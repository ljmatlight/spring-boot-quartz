package org.galsang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 主程序
 *
 * @author tengpeng.gao
 * @since 2018/8/21
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringBootQuartzApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzApp.class, args);
    }

}
