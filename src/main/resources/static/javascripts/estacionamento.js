var Estacionamento = Estacionamento || {};

Estacionamento.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
//		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
//		this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.decimal.maskNumber({ decimal: ',', thousands: '.' });
		this.plain.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskMoney;
	
}());

Estacionamento.MaskPlaca = (function() {
	
	function MaskPlaca() {
		this.inputPlaca = $('#placa');
	}
	
	MaskPlaca.prototype.enable = function() {
		this.inputPlaca.inputmask({mask: 'AAA-9999'});
	}
	
	return MaskPlaca;
	
}());

Estacionamento.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000 00:00');
	}
	
	return MaskDate;
	
}());

Estacionamento.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
}());	

numeral.language('pt-br');

Estacionamento.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

Estacionamento.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function() {
	var maskMoney = new Estacionamento.MaskMoney();
	maskMoney.enable();
	
	var maskPlaca = new Estacionamento.MaskPlaca();
	maskPlaca.enable();
	
	var maskDate = new Estacionamento.MaskDate();
	maskDate.enable();
	
	var security = new Estacionamento.Security();
	security.enable();
});
