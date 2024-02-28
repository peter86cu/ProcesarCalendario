package com.batch.marcas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProcesarCalendarioEmpleados {

	public static void main(String[] args) {
		SpringApplication.run(ProcesarCalendarioEmpleados.class, args);
	}

}
