package com.estacionamento.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.estacionamento.model.Veiculo;

public class VeiculoConverter implements Converter<String, Veiculo>{

	@Override
	public Veiculo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Veiculo veiculo = new Veiculo();
			veiculo.setCodigo(Long.valueOf(codigo));
			return veiculo;
		}

		return null;
	}

}
