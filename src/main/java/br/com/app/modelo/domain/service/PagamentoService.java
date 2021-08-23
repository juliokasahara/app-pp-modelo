package br.com.app.modelo.domain.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.app.modelo.domain.DTO.PagamentoDTO;
import br.com.app.modelo.domain.model.Pagamento;

public interface PagamentoService{

	Page<Pagamento> findAll(Pageable pageable);

	Pagamento findByIdOrThrowBadRequest(long id);

	void delete(long id);

	Pagamento save(PagamentoDTO pagamentoDTO);

	void update(PagamentoDTO pagamentoDTO);

	void pagar(@Valid PagamentoDTO pagamentoDTO);

}
