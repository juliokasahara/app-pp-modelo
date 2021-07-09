package br.com.app.modelo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.app.modelo.domain.model.Produto;
import br.com.app.modelo.domain.request.ProdutoPostRequest;
import br.com.app.modelo.domain.request.ProdutoPutRequest;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {	
	public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	@Mapping(target = "idProduto", ignore = true)
	public abstract Produto toProduto(ProdutoPostRequest produtoPostRequest);
	
	public abstract Produto toProduto(ProdutoPutRequest produtoPutRequest);
}
