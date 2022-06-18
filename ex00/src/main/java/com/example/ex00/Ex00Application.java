package com.example.ex00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Ex00Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex00Application.class, args);
    }

}
