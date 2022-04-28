package com.example.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.example.entity" })
@ServletComponentScan(basePackages = {
		"com.example.filter"
})
@ComponentScan(basePackages = {
		"com.example.config",
		"com.example.controller",
		"com.example.restcontroller",
		"com.example.service",
		"com.example.serviceImpl",
		"com.example.jwt",
})
// 저장소(jpa) == 매퍼(mybatis)
@EnableJpaRepositories(basePackages = {
		"com.example.repository"
})
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

}
