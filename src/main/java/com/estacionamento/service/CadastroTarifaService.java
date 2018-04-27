package com.estacionamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estacionamento.model.Tarifa;
import com.estacionamento.repository.Tarifas;
import com.estacionamento.service.exception.DescricaoTarifaJaCadastradaException;

@Service
public class CadastroTarifaService {
	
	@Autowired
	private Tarifas tarifas;
	
	@Transactional
	public Tarifa salvar(Tarifa tarifa) {
		Optional<Tarifa> tarifaOptional = tarifas.findByDescricaoIgnoreCase(tarifa.getDescricao());
		if (tarifaOptional.isPresent()) {
			throw new DescricaoTarifaJaCadastradaException("Tarifa ja cadastrada");
		}
		
		return tarifas.saveAndFlush(tarifa);
	}

}
