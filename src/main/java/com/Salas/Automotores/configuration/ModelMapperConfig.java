package com.Salas.Automotores.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper ModelMapper() {
		ModelMapper modelmapper = new ModelMapper();
		return modelmapper;
	}
}
