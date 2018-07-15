package com.ccr.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("JVM正在关闭.....");
		}));
	}
}
