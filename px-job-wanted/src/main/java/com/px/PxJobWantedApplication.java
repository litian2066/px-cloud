package com.px;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PxJobWantedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PxJobWantedApplication.class, args);
    }

}
