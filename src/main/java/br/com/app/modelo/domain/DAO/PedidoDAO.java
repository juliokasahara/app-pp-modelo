package br.com.app.modelo.domain.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.modelo.domain.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long>{
	
	
}
