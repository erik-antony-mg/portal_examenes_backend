package com.sistema.examenes;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SistemaExamenesApplication {
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
//		return NoOpPasswordEncoder.getInstance();
	}



	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesApplication.class, args);
	}

}
