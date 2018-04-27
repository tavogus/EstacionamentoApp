package com.estacionamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.model.Tarifa;
import com.estacionamento.repository.helper.tarifa.TarifasQueries;

public interface Tarifas extends JpaRepository<Tarifa,Long>, TarifasQueries{

	Optional<Tarifa> findByDescricaoIgnoreCase(String descricao);

}
