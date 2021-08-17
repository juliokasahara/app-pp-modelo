package br.com.app.modelo.domain.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.modelo.domain.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByNome(String nome);
}
