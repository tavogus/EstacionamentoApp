package com.estacionamento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estacionamento.controller.page.PageWrapper;
import com.estacionamento.model.Movimentacao;
import com.estacionamento.repository.Movimentacoes;
import com.estacionamento.repository.filter.MovimentacaoFilter;
import com.estacionamento.service.CadastroMovimentacaoService;

@Controller
@RequestMapping("/movimentacoes")
public class MovimentacoesController {
	
	@Autowired
	private CadastroMovimentacaoService cadastroMovimentacaoService;
	
	@Autowired
	private Movimentacoes movimentacoes;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Movimentacao movimentacao) {
		return new ModelAndView("movimentacao/CadastroMovimentacao");
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(movimentacao);
		}
		cadastroMovimentacaoService.salvar(movimentacao);

		attributes.addFlashAttribute("mensagem", "Movimentacao salva com sucesso");
		return new ModelAndView("redirect:/movimentacoes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(MovimentacaoFilter movimentacaoFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("movimentacao/PesquisaMovimentacao");
		
		PageWrapper<Movimentacao> paginaWrapper = new PageWrapper<>(movimentacoes.filtrar(movimentacaoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView fecharMv(@PathVariable("codigo") Movimentacao movimentacao) {
		ModelAndView mv = new ModelAndView("movimentacao/FecharMovimentacao"); 
		mv.addObject(movimentacao);
		return mv;
	}
	
	@PostMapping("/fechar")
	public ModelAndView fechar(Movimentacao movimentacao) {
		cadastroMovimentacaoService.fechar(movimentacao);
		/*return new ModelAndView("redirect:/movimentacoes");*/
		 Movimentacao movimentacaoFechada = cadastroMovimentacaoService.fechar(movimentacao);
		return fecharMv(movimentacaoFechada);
		
	}
	

}
