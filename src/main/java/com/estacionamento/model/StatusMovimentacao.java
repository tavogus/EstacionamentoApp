package com.estacionamento.model;

public enum StatusMovimentacao {
	
	ORCAMENTO("Orçamento"), 
	FECHADA("Fechada");

	private String descricao;

	StatusMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}
