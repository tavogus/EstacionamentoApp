package com.estacionamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.helper.veiculo.VeiculosQueries;

public interface Veiculos extends JpaRepository<Veiculo,Long>, VeiculosQueries{

	Optional<Veiculo> findByPlacaIgnoreCase(String placa);

	public List<Veiculo> findByPlacaStartingWithIgnoreCase(String placa);

}
