package com.giousa.daily;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.giousa.daily"})
@MapperScan(basePackages = "com.giousa.daily.mapper")
@EnableTransactionManagement//开启事务支持
@ServletComponentScan
public class DailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
    }

}
