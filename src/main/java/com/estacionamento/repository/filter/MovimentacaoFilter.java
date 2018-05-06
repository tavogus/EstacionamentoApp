package com.estacionamento.repository.filter;

import java.time.LocalDateTime;

import com.estacionamento.model.StatusMovimentacao;

public class MovimentacaoFilter {

    private String veiculo;
    
    private StatusMovimentacao status;

	private LocalDateTime dataEntrada;
	
	private LocalDateTime dataSaida;

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public StatusMovimentacao getStatus() {
		return status;
	}

	public void setStatus(StatusMovimentacao status) {
		this.status = status;
	}

	
	
}
