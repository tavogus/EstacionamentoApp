package com.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.model.Movimentacao;
import com.estacionamento.repository.helper.movimentacao.MovimentacoesQueries;

public interface Movimentacoes extends JpaRepository<Movimentacao,Long>, MovimentacoesQueries{

}
