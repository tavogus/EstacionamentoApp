package com.estacionamento.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.estacionamento.model.Movimentacao;

@Component
public class MovimentacaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Movimentacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "veiculo.codigo", "", "Selecione um veiculo na pesquisa rápida");
	}


}
