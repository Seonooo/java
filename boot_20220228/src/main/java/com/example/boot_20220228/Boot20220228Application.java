package com.example.boot_20220228;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@SpringBootApplication
@EnableMongoHttpSession(collectionName = "sessions", maxInactiveIntervalInSeconds = 1800)
@ComponentScan(basePackages = {"com.example.controller", "com.example.service"})
public class Boot20220228Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220228Application.class, args);
	}

	// @Bean => 프로그램이 구동되기 전에 미리 만들어지는 객체
	@Bean
	public JacksonMongoSessionConverter mongoSessionConverter(){
		return new JacksonMongoSessionConverter();
	}

}
