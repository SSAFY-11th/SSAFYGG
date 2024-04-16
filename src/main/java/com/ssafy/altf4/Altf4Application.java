package com.ssafy.altf4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.ssafy.altf4")
public class Altf4Application {

	public static void main(String[] args) {
		SpringApplication.run(Altf4Application.class, args);
	}

}
