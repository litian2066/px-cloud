package com.px;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class PxZuul9002Application {

    public static void main(String[] args) {
        SpringApplication.run(PxZuul9002Application.class, args);
    }

}
