package com.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacionamento.model.Grupo;



public interface Grupos extends JpaRepository<Grupo, Long> {

}
