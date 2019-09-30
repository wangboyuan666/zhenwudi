package com.tedu.sp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
public class Sp02HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp02HystrixDashboardApplication.class, args);
	}

}
