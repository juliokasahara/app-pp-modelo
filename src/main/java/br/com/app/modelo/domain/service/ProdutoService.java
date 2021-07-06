package br.com.app.modelo.domain.service;

import java.util.List;

import br.com.app.modelo.domain.model.Produto;
import br.com.app.modelo.domain.request.ProdutoPostRequest;
import br.com.app.modelo.domain.request.ProdutoPutRequest;

public interface ProdutoService{

	List<Produto> findAll();
	
	List<Produto> findByName(String nome);

	Produto findByIdOrThrowBadRequest(long id);

	void delete(long id);

	Produto save(ProdutoPostRequest produtoPostRequest);

	void update(ProdutoPutRequest produtoPutRequest);

}
