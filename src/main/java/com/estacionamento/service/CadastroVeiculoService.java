package com.estacionamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.Veiculos;
import com.estacionamento.service.exception.PlacaVeiculoJaCadastradaException;

@Service
public class CadastroVeiculoService {

	@Autowired
	private Veiculos veiculos;
	
	@Transactional
	public Veiculo salvar(Veiculo veiculo) {
		Optional<Veiculo> veiculoOptional = veiculos.findByPlacaIgnoreCase(veiculo.getPlaca());
		if (veiculoOptional.isPresent()) {
			throw new PlacaVeiculoJaCadastradaException("Veiculo ja cadastrado");
		}
		
		return veiculos.saveAndFlush(veiculo);
	}
}
