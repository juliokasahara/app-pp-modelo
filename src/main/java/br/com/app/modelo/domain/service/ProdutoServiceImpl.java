package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.modelo.domain.DAO.ProdutoDAO;
import br.com.app.modelo.domain.DTO.ProdutoPostDTO;
import br.com.app.modelo.domain.DTO.ProdutoPutDTO;
import br.com.app.modelo.domain.exception.BadRequestException;
import br.com.app.modelo.domain.mapper.ProdutoMapper;
import br.com.app.modelo.domain.model.Produto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{
	
	private final ProdutoDAO produtoDAO;

	public Page<Produto> findAll(Pageable pageable) {
		return produtoDAO.findAll(pageable);
	}
	
	public List<Produto> findByName(String nome) {
		return produtoDAO.findByNome(nome);
	}

	public Produto findByIdOrThrowBadRequest(long id) {
		return produtoDAO.findById(id).orElseThrow(() -> new BadRequestException("Produto não existe"));
	}

	public void delete(long id) {
		produtoDAO.delete(findByIdOrThrowBadRequest(id));
		
	}
	
//	@Transactional(rollbackFor = Exception.class) checked unchecked exception
	@Transactional
	public Produto save(ProdutoPostDTO produtoPostRequest) {
		return produtoDAO.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequest));
	}

	public void update(ProdutoPutDTO produtoPutRequest) {
		Produto produtoBanco = findByIdOrThrowBadRequest(produtoPutRequest.getIdProduto());
		Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequest);
		produto.setIdProduto(produtoBanco.getIdProduto());
		produtoDAO.save(produto);
	}

}
