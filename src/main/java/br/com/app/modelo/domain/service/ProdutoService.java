package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.app.modelo.domain.DTO.ProdutoDTO;
import br.com.app.modelo.domain.model.Produto;

public interface ProdutoService{

	Page<Produto> findAll(Pageable pageable);
	
	List<Produto> findByName(String nome);

	Produto findByIdOrThrowBadRequest(long id);

	void delete(long id);

	Produto save(ProdutoDTO produtoDTO);

	void update(ProdutoDTO produtoDTO);

}
