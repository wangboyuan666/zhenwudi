package com.tedu.sp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Sp02EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp02EurekaApplication.class, args);
	}

}
