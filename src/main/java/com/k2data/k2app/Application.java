package com.k2data.k2app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log4j2
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("SpringBoot Start Success");
    }

}

