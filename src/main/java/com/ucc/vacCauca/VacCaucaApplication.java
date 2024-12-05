package com.ucc.vacCauca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableScheduling
@SpringBootApplication
@EntityScan("com.ucc.vacCauca.domain")
public class VacCaucaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacCaucaApplication.class, args);
	}
}
