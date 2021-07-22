package br.com.app.modelo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.app.modelo.domain.DTO.ProdutoPostDTO;
import br.com.app.modelo.domain.DTO.ProdutoPutDTO;
import br.com.app.modelo.domain.model.Produto;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {	
	public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	public abstract Produto toProduto(ProdutoPostDTO produtoPostRequest);
	
	public abstract Produto toProduto(ProdutoPutDTO produtoPutRequest);
}
