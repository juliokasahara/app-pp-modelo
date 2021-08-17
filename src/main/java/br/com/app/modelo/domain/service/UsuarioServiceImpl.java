package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.modelo.domain.DAO.UsuarioDAO;
import br.com.app.modelo.domain.DTO.UsuarioDTO;
import br.com.app.modelo.domain.exception.BadRequestException;
import br.com.app.modelo.domain.mapper.UsuarioMapper;
import br.com.app.modelo.domain.model.Usuario;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
	
	private final UsuarioDAO usuarioDAO;

	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDAO.findAll(pageable);
	}
	
	public List<Usuario> findByName(String nome) {
		return usuarioDAO.findByNome(nome);
	}

	public Usuario findByIdOrThrowBadRequest(long id) {
		return usuarioDAO.findById(id).orElseThrow(() -> new BadRequestException("Usuario não existe"));
	}

	public void delete(long id) {
		usuarioDAO.delete(findByIdOrThrowBadRequest(id));
		
	}
	
//	checked unchecked exception
	@Transactional(rollbackFor = Exception.class)
	public Usuario save(UsuarioDTO usuarioDTO) {
		
		return usuarioDAO.save(UsuarioMapper.INSTANCE.toUsuario(usuarioDTO));
	}

	public void update(UsuarioDTO usuarioDTO) {
		Usuario usuarioBanco = findByIdOrThrowBadRequest(usuarioDTO.getIdUsuario());
		Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
		usuario.setIdUsuario(usuarioBanco.getIdUsuario());
		usuarioDAO.save(usuario);
	}

}
