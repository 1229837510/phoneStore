package com.bigdata.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class PhoneStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneStoreApplication.class, args);

    }

}
