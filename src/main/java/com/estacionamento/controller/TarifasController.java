package com.estacionamento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estacionamento.controller.page.PageWrapper;
import com.estacionamento.model.Tarifa;
import com.estacionamento.repository.Tarifas;
import com.estacionamento.repository.filter.TarifaFilter;
import com.estacionamento.service.CadastroTarifaService;
import com.estacionamento.service.exception.DescricaoTarifaJaCadastradaException;

@Controller
@RequestMapping("/tarifas")
public class TarifasController {
	
	@Autowired
	private CadastroTarifaService cadastroTarifaService;
	
	@Autowired
	private Tarifas tarifas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Tarifa tarifa) {
		return new ModelAndView("tarifa/CadastroTarifa");
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Tarifa tarifa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tarifa);
		}
		
		try {
			cadastroTarifaService.salvar(tarifa);
		} catch (DescricaoTarifaJaCadastradaException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(tarifa);
		}
		
		attributes.addFlashAttribute("mensagem", "Tarifa salvo com sucesso");
		return new ModelAndView("redirect:/tarifas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(TarifaFilter tarifaFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("tarifa/PesquisaTarifa");
		
		PageWrapper<Tarifa> paginaWrapper = new PageWrapper<>(tarifas.filtrar(tarifaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	

}
