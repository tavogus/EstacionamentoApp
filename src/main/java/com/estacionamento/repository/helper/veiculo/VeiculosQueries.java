package com.estacionamento.repository.helper.veiculo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.filter.VeiculoFilter;

public interface VeiculosQueries {
	
	public Page<Veiculo> filtrar(VeiculoFilter filtro, Pageable pageable);

}
