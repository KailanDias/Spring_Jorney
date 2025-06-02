package com.github.KailanDias.arquiteruraspring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ArquiteruraspringApplication {

	public static void main(String[] args) {

//		SpringApplication.run(ArquiteruraspringApplication.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ArquiteruraspringApplication.class);

		builder.bannerMode(Banner.Mode.OFF);
		ConfigurableApplicationContext applicationContext = builder.context();

//		builder.profiles("Producao", "Homologacao");
		builder.run(args);
	}

}
