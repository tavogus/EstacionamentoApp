package com.estacionamento.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.estacionamento.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.estacionamento.thymeleaf.processor.MenuAttributeTagProcessor;
import com.estacionamento.thymeleaf.processor.MessageElementTagProcessor;
import com.estacionamento.thymeleaf.processor.OrderElementTagProcessor;
import com.estacionamento.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class EstacionamentoDialect extends AbstractProcessorDialect {

	public EstacionamentoDialect() {
		super("Estacionamento App", "estacionamento", StandardDialect.PROCESSOR_PRECEDENCE);

	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
