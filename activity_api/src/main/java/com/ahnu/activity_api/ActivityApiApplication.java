package com.ahnu.activity_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ahnu.activity_api.controller","com.ahnu.activity_api.service","com.ahnu.activity_api.dao","com.ahnu.activity_api.interceptor"})
@MapperScan(basePackages = {"com.ahnu.activity_api.dao"})
public class ActivityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApiApplication.class, args);
    }

}
