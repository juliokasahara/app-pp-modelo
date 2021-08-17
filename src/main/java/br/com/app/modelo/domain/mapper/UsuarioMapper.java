package br.com.app.modelo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.app.modelo.domain.DTO.UsuarioDTO;
import br.com.app.modelo.domain.model.Usuario;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {	
	public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	public abstract Usuario toUsuario(UsuarioDTO usuarioDTO);
	
}