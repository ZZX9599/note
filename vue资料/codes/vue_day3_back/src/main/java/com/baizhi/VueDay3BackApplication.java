package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class VueDay3BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueDay3BackApplication.class, args);
    }

}
