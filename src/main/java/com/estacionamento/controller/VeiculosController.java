package com.estacionamento.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estacionamento.controller.page.PageWrapper;
import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.Tarifas;
import com.estacionamento.repository.Veiculos;
import com.estacionamento.repository.filter.VeiculoFilter;
import com.estacionamento.service.CadastroVeiculoService;
import com.estacionamento.service.exception.PlacaVeiculoJaCadastradaException;

@Controller
@RequestMapping("/veiculos")
public class VeiculosController {

	@Autowired
	private CadastroVeiculoService cadastroVeiculoSerive;

	@Autowired
	private Veiculos veiculos;

	@Autowired
	private Tarifas tarifas;

	@RequestMapping("/novo")
	public ModelAndView novo(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView("veiculo/CadastroVeiculo");
		mv.addObject("tarifas", tarifas.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(veiculo);
		}

		try {
			cadastroVeiculoSerive.salvar(veiculo);
		} catch (PlacaVeiculoJaCadastradaException e) {
			result.rejectValue("placa", e.getMessage(), e.getMessage());
			return novo(veiculo);
		}

		attributes.addFlashAttribute("mensagem", "Veiculo salvo com sucesso");
		return new ModelAndView("redirect:/veiculos/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar(VeiculoFilter veiculoFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("veiculo/PesquisaVeiculo");
		
		PageWrapper<Veiculo> paginaWrapper = new PageWrapper<>(veiculos.filtrar(veiculoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Veiculo> pesquisar(String placa) {
		return veiculos.findByPlacaStartingWithIgnoreCase(placa);
	}
}
