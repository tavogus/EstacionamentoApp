package com.estacionamento.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estacionamento.controller.page.PageWrapper;
import com.estacionamento.controller.validator.MovimentacaoValidator;
import com.estacionamento.model.Movimentacao;
import com.estacionamento.model.StatusMovimentacao;
import com.estacionamento.repository.Movimentacoes;
import com.estacionamento.repository.filter.MovimentacaoFilter;
import com.estacionamento.security.UsuarioSistema;
import com.estacionamento.service.CadastroMovimentacaoService;
import com.estacionamento.service.exception.DataSaidaMenorQueDataEntrada;
import com.estacionamento.service.exception.DataSaidaVazia;

@Controller
@RequestMapping("/movimentacoes")
public class MovimentacoesController {

	@Autowired
	private CadastroMovimentacaoService cadastroMovimentacaoService;

	@Autowired
	private Movimentacoes movimentacoes;

	@Autowired
	private MovimentacaoValidator movimentacaoValidator;

	@RequestMapping("/novo")
	public ModelAndView novo(Movimentacao movimentacao) {
		return new ModelAndView("movimentacao/CadastroMovimentacao");
	}

	@PostMapping("/novo")
	public ModelAndView cadastrar(Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes,@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarMovimentacao(movimentacao, result);
		if (result.hasErrors()) {
			return novo(movimentacao);
		}
		movimentacao.setUsuario(usuarioSistema.getUsuario());

		cadastroMovimentacaoService.salvar(movimentacao);

		attributes.addFlashAttribute("mensagem", "Movimentacao salva com sucesso");
		return new ModelAndView("redirect:/movimentacoes/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(MovimentacaoFilter movimentacaoFilter, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("movimentacao/PesquisaMovimentacao");
		mv.addObject("todosStatus", StatusMovimentacao.values());
		PageWrapper<Movimentacao> paginaWrapper = new PageWrapper<>(movimentacoes.filtrar(movimentacaoFilter, pageable),httpServletRequest);
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
	public ModelAndView fechar(Movimentacao movimentacao, BindingResult result) {

		Movimentacao movimentacaoFechada = null;
		try {
			movimentacaoFechada = cadastroMovimentacaoService.fechar(movimentacao);
		} catch (DataSaidaMenorQueDataEntrada e) {
			result.rejectValue("dataHoraSaida", e.getMessage(), e.getMessage());
			return fecharMv(movimentacao);
		} catch (DataSaidaVazia e) {
			result.rejectValue("dataHoraSaida", e.getMessage(), e.getMessage());
			return fecharMv(movimentacao);
		}
		return fecharMv(movimentacaoFechada);
	}

	private void validarMovimentacao(Movimentacao movimentacao, BindingResult result) {
		movimentacaoValidator.validate(movimentacao, result);
	}

}
