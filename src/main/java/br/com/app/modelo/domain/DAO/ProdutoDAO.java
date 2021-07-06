package br.com.app.modelo.domain.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.modelo.domain.model.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long>{
	
	List<Produto> findByNome(String nome);
}
