package com.example.boot_20220328;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // aop 추가
@ComponentScan(basePackages = { "com.example.controller", "com.example.service", "com.example.config",
		"com.example.aop", "com.example.interceptor" })
@MapperScan(basePackages = { "com.example.mapper" })
public class Boot20220328Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220328Application.class, args);
	}

}
