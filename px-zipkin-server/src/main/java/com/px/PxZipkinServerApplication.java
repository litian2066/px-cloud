package com.px;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class PxZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PxZipkinServerApplication.class, args);
    }

}
