Estacionamento = Estacionamento || {};

Estacionamento.PesquisaRapidaVeiculo = (function() {
	
	function PesquisaRapidaVeiculo() {
		this.pesquisaRapidaVeiculosModal = $('#pesquisaRapidaVeiculos');
		this.placaInput = $('#placaVeiculoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-veiculos-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaVeiculos');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-veiculo').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaVeiculo.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaVeiculosModal.on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.placaInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaVeiculosModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				placa: this.placaInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		console.log(this.placaInput.val())
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaVeiculoPesquisaRapida = new Estacionamento.TabelaVeiculoPesquisaRapida(this.pesquisaRapidaVeiculosModal);
		tabelaVeiculoPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaVeiculo;
	
}());

Estacionamento.TabelaVeiculoPesquisaRapida = (function() {
	
	function TabelaVeiculoPesquisaRapida(modal) {
		this.modalVeiculo = modal;
		this.veiculo = $('.js-veiculo-pesquisa-rapida');
	}
	
	TabelaVeiculoPesquisaRapida.prototype.iniciar = function() {
		this.veiculo.on('click', onVeiculoSelecionado.bind(this));
	}
	
	function onVeiculoSelecionado(evento) {
		this.modalVeiculo.modal('hide');
		
		var veiculoSelecionado = $(evento.currentTarget);
		$('#placaVeiculo').val(veiculoSelecionado.data('placa'));
		$('#codigoVeiculo').val(veiculoSelecionado.data('codigo'));
	}
	
	return TabelaVeiculoPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaVeiculo = new Estacionamento.PesquisaRapidaVeiculo();
	pesquisaRapidaVeiculo.iniciar();
});