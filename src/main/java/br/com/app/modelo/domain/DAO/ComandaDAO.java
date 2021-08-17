package br.com.app.modelo.domain.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.modelo.domain.model.Comanda;

public interface ComandaDAO extends JpaRepository<Comanda, Long>{
	
	List<Comanda> findByNome(String nome);
	
	@Query("SELECT c FROM Comanda c " +
		   "INNER JOIN fetch c.pedidos p "	+
		   "WHERE p.comanda.idComanda = :id "	
	)
	public Comanda comadaDetalhe(long id);
	
}
