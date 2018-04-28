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

Estacionamento.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
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
	
	var maskDate = new Estacionamento.MaskDate();
	maskDate.enable();	
	
	var maskPlaca = new Estacionamento.MaskPlaca();
	maskPlaca.enable();
});
