package com.legodo.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
@EnableCircuitBreaker
@ComponentScan
@EnableAutoConfiguration
public class FootballAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballAnalyzerApplication.class, args);					
	}
	
}
