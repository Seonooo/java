package com.example.boot_20220228;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@SpringBootApplication

// 임의로 만들 컨트롤러 서비스의 위치
@ComponentScan(basePackages = { "com.example.controller", "com.example.service" })

// 임의로 만든저장소 위치
@EnableMongoRepositories(basePackages = { "com.example.repository" })

@EnableMongoHttpSession(collectionName = "sessions", maxInactiveIntervalInSeconds = 1800)
public class Boot20220228Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220228Application.class, args);
	}

	// @Bean => 프로그램이 구동되기 전에 미리 만들어지는 객체
	// public JacksonMongoSessionConverter mongoSessionConverter(){
	// return new JacksonMongoSessionConverter();
	// }

	@Bean
	public JdkMongoSessionConverter mongoSessionConverter() {
		return new JdkMongoSessionConverter(Duration.ofMinutes(30));
	}

}
