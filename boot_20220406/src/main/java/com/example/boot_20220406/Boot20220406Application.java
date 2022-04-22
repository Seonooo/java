package com.example.boot_20220406;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 정의 변수 설정
@PropertySource("classpath:global.properties")
@ServletComponentScan(basePackages = { "com.example.filter" })
// 컨트롤러, 환경파일, 서비스
@ComponentScan(basePackages = { "com.example.controller", "com.example.service",
		"com.example.config", "com.example.restcontroller", "com.example.jwt", "com.example.schedule" })
@MapperScan(basePackages = { "com.example.mapper" })
@EntityScan(basePackages = { "com.example.entity" })
// 저장소(jpa) == 매퍼(mybatis)
@EnableJpaRepositories(basePackages = { "com.example.repository" })
public class Boot20220406Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220406Application.class, args);

	}

}