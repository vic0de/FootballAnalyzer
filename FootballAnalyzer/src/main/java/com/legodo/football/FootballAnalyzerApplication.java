package com.legodo.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;



@SpringBootApplication
@EnableHystrixDashboard
@EnableFeignClients
@EnableCircuitBreaker
public class FootballAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballAnalyzerApplication.class, args);					
	}
	
}
