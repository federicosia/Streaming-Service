package com.tesistio.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FilmApplication_v1 {
    public static void main(String[] args) {
        SpringApplication.run(FilmApplication_v1.class, args);
    }
}