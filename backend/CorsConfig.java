package com.rewards.backend;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] origins = new String[] {"http://localhost:4200"};
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("GET","POST","PUT", "DELETE")
		.allowedOrigins(origins);
	}
}
