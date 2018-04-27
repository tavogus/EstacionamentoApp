package com.estacionamento.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.estacionamento.model.Tarifa;

public class TarifaConverter implements Converter<String, Tarifa> {

	@Override
	public Tarifa convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Tarifa tarifa = new Tarifa();
			tarifa.setCodigo(Long.valueOf(codigo));
			return tarifa;
		}

		return null;

	}
}
