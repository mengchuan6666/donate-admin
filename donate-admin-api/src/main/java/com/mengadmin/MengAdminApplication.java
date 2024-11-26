package com.mengadmin;

import com.mengadmin.common.core.config.ConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * Created by EleAdmin on 2018-02-22 11:29:03
 */
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.mengadmin.**.mapper")
@EnableConfigurationProperties(ConfigProperties.class)
@SpringBootApplication
public class MengAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MengAdminApplication.class, args);
    }

}
