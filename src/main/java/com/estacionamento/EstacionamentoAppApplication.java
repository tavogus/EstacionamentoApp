package com.estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EstacionamentoAppApplication {

	private static ApplicationContext APPLICATION_CONTEXT;

	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(EstacionamentoAppApplication.class, args);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return APPLICATION_CONTEXT.getBean(requiredType);
	}
}
