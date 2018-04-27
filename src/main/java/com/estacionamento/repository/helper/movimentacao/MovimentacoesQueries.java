package com.estacionamento.repository.helper.movimentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estacionamento.model.Movimentacao;
import com.estacionamento.repository.filter.MovimentacaoFilter;

public interface MovimentacoesQueries {
	
	public Page<Movimentacao> filtrar(MovimentacaoFilter filtro, Pageable pageable);

}
