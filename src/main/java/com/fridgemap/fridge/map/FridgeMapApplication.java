package com.fridgemap.fridge.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@SpringBootApplication
@EnableJpaRepositories
public class FridgeMapApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(FridgeMapApplication.class, args);
		System.out.println("Hello world!");
	}

	// Source - https://stackoverflow.com/a
// Posted by Faraj Farook
// Retrieved 2025-12-21, License - CC BY-SA 3.0
//For path to /static/css function only
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/static/css/**")) {
			registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
		}

		if (!registry.hasMappingForPattern("/static/images/**")) {
			registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
		}
	}
}
