package br.com.app.modelo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.app.modelo.domain.DTO.ComandaDTO;
import br.com.app.modelo.domain.model.Comanda;

@Mapper(componentModel = "spring")
public abstract class ComandaMapper {	
	public static final ComandaMapper INSTANCE = Mappers.getMapper(ComandaMapper.class);
	
	public abstract Comanda toComanda(ComandaDTO comandaDTO);
	
}