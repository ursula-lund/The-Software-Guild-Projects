package com.sg.supers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class SupersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupersApplication.class, args);
    }

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
            value = CommandLineRunner.class))
    @EnableAutoConfiguration
    public class TestApplicationConfiguration {

    }
}
