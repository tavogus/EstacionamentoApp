package com.estacionamento.service.exception;

public class DataSaidaMenorQueDataEntrada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataSaidaMenorQueDataEntrada(String message) {
		super(message);

	}

}
