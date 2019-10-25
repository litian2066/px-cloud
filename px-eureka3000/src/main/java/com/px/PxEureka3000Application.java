package com.px;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PxEureka3000Application {

    public static void main(String[] args) {
        SpringApplication.run(PxEureka3000Application.class, args);
    }

}
