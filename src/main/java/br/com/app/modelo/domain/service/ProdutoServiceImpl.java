package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.app.modelo.domain.DAO.ProdutoDAO;
import br.com.app.modelo.domain.mapper.ProdutoMapper;
import br.com.app.modelo.domain.model.Produto;
import br.com.app.modelo.domain.request.ProdutoPostRequest;
import br.com.app.modelo.domain.request.ProdutoPutRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{
	
	private final ProdutoDAO produtoDAO;

	public List<Produto> findAll() {
		return produtoDAO.findAll();
	}
	
	public List<Produto> findByName(String nome) {
		return produtoDAO.findByNome(nome);
	}

	public Produto findByIdOrThrowBadRequest(long id) {
		return produtoDAO.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe"));
	}

	public void delete(long id) {
		produtoDAO.delete(findByIdOrThrowBadRequest(id));
		
	}
	
	public Produto save(ProdutoPostRequest produtoPostRequest) {
		return produtoDAO.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequest));
	}

	public void update(ProdutoPutRequest produtoPutRequest) {
		Produto produtoBanco = findByIdOrThrowBadRequest(produtoPutRequest.getIdProduto());
		Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequest);
		produto.setIdProduto(produtoBanco.getIdProduto());
		produtoDAO.save(produto);
	}

}
