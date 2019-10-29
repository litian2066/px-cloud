package com.px;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class PxHystrixDashborApplication {

	public static void main(String[] args) {
		SpringApplication.run(PxHystrixDashborApplication.class, args);
	}

}
