package com.fxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fxs.dao")
public class CmfzFxsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzFxsApplication.class, args);
    }

}
