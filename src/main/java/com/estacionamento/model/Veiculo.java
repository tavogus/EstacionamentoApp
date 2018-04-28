package com.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.validator.constraints.NotBlank;

import com.estacionamento.validation.PLACA;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@PLACA
	@NotBlank(message = "placa é obrigatório")
	private String placa;
	
	@NotBlank(message = "modelo é obrigatório")
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name = "tarifa")
    private Tarifa tarifa;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		placa = placa.toUpperCase();
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}


	

}
