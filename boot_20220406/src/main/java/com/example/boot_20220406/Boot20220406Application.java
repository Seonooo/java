package com.example.boot_20220406;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

@PropertySource("classpath:global.properties")
// 컨트롤러, 환경파일, 서비스
@ComponentScan(basePackages = { "com.example.controller", "com.example.service",
		"com.example.config", "com.example.restcontroller" })
@MapperScan(basePackages = { "com.example.mapper" })
public class Boot20220406Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220406Application.class, args);

	}

}