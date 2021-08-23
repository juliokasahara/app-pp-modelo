package br.com.app.modelo.domain.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.modelo.domain.model.Pagamento;

public interface PagamentoDAO extends JpaRepository<Pagamento, Long>{
	
}
