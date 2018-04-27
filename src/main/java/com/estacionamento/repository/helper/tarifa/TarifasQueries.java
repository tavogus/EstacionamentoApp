package com.estacionamento.repository.helper.tarifa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estacionamento.model.Tarifa;
import com.estacionamento.repository.filter.TarifaFilter;


public interface TarifasQueries {
	
	public Page<Tarifa> filtrar(TarifaFilter filtro, Pageable pageable);

}
