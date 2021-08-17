package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.app.modelo.domain.DTO.ComandaDTO;
import br.com.app.modelo.domain.model.Comanda;

public interface ComandaService{

	Page<Comanda> findAll(Pageable pageable);
	
	List<Comanda> findByName(String nome);

	Comanda findByIdOrThrowBadRequest(long id);

	void delete(long id);

	Comanda save(ComandaDTO comandaDTO);

	void update(ComandaDTO comandaDTO);

}
