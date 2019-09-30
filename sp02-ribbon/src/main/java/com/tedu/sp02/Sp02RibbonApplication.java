package com.tedu.sp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
public class Sp02RibbonApplication {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setReadTimeout(1000);
		f.setConnectTimeout(1000);
		return new RestTemplate(f);
	}
	public static void main(String[] args) {
		SpringApplication.run(Sp02RibbonApplication.class, args);
	}

}
