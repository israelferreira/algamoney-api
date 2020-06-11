package com.algaworks.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.algaworks.algamoney.api.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AlgamoneyApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}


}
