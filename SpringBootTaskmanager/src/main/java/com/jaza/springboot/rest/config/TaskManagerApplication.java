package com.jaza.springboot.rest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.jaza.springboot.rest")
@PropertySource("classpath:database.properties")
public class TaskManagerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
}
