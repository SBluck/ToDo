package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

	@Bean
	@Scope("prototype")
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	// makes object mapping easy by automatically determining how one object e.g.
	// domain maps to another e.g. dto
}
