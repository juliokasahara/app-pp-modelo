package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.app.modelo.domain.DTO.UsuarioDTO;
import br.com.app.modelo.domain.model.Usuario;

public interface UsuarioService{

	Page<Usuario> findAll(Pageable pageable);
	
	List<Usuario> findByName(String nome);

	Usuario findByIdOrThrowBadRequest(long id);

	void delete(long id);

	Usuario save(UsuarioDTO usuarioDTO);

	void update(UsuarioDTO usuarioDTO);

}
