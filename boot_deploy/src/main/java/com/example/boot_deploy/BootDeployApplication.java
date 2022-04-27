// 파일명 : BootDeployApplication.java
package com.example.boot_deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.controller")
public class BootDeployApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootDeployApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootDeployApplication.class);
	}

}