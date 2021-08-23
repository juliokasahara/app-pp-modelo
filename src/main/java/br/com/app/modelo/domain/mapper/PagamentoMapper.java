package br.com.app.modelo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.app.modelo.domain.DTO.PagamentoDTO;
import br.com.app.modelo.domain.model.Pagamento;

@Mapper(componentModel = "spring")
public abstract class PagamentoMapper {	
	public static final PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);
	
	public abstract Pagamento toPagamento(PagamentoDTO pagamentoDTO);
	
}