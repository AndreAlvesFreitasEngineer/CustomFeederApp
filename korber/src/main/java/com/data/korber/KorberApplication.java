package com.data.korber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KorberApplication {

	public static void main(String[] args) {
		SpringApplication.run(KorberApplication.class, args);
	}

}
