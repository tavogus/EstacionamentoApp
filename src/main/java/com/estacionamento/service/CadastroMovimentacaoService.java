package com.estacionamento.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.model.Movimentacao;
import com.estacionamento.model.StatusMovimentacao;
import com.estacionamento.repository.Movimentacoes;
import com.estacionamento.service.exception.DataSaidaMenorQueDataEntrada;
import com.estacionamento.service.exception.DataSaidaVazia;

@Service
public class CadastroMovimentacaoService {

	@Autowired
	private Movimentacoes movimentacoes;

	@Transactional
	public Movimentacao salvar(Movimentacao movimentacao) {
		if (movimentacao.isNova()) {
			movimentacao.setDataHoraEntrada(LocalDateTime.now());
		} else {
			Movimentacao movimentacaoExistente = movimentacoes.getOne(movimentacao.getCodigo());
			movimentacao.setDataHoraEntrada(movimentacaoExistente.getDataHoraEntrada());
		}
		return movimentacoes.saveAndFlush(movimentacao);
	}

	@Transactional
	public Movimentacao fechar(Movimentacao movimentacao) {
		Movimentacao movimentacaoExistente = movimentacoes.getOne(movimentacao.getCodigo());
		LocalDateTime entrada = movimentacaoExistente.getDataHoraEntrada();
		LocalDateTime saida = movimentacao.getDataHoraSaida();
		BigDecimal valorTarifa = movimentacaoExistente.getVeiculo().getTarifa().getValor();
		
		if (saida == null) {
			throw new DataSaidaVazia("Data hora saida não pode ficar vazia no fechamento");
		}

		if (saida.isBefore(entrada)) {
			throw new DataSaidaMenorQueDataEntrada("Data saida menor que data entrada");
		}
		
		long diferencaHoras = ChronoUnit.HOURS.between(entrada, saida);
		long valorTotal = (valorTarifa.longValue() * diferencaHoras);

		movimentacaoExistente.setDataHoraSaida(saida);
		movimentacaoExistente.setTotal(valorTotal);
		movimentacaoExistente.setStatus(StatusMovimentacao.FECHADA);
		return salvar(movimentacaoExistente);
	}

}
