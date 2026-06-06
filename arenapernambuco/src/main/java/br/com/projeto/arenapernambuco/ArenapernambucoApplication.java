package br.com.projeto.arenapernambuco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ArenapernambucoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArenapernambucoApplication.class, args);
	}

}