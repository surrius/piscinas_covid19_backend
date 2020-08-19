package com.surrius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PiscinasDurueloCovid19Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PiscinasDurueloCovid19Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PiscinasDurueloCovid19Application.class, args);
	}
}
