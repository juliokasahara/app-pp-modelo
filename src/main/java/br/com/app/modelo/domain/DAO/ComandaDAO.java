package br.com.app.modelo.domain.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.modelo.domain.model.Comanda;

public interface ComandaDAO extends JpaRepository<Comanda, Long>{
	
	List<Comanda> findByNome(String nome);
	
//	@Query("SELECT c FROM Comanda c " +
//		   "INNER JOIN fetch c.pedidos p "	+
//		   "WHERE p.comanda.idComanda = :id "	
//	)
//	public Comanda comadaDetalhe(long id);

//	@Query("SELECT c From Comanda c " +
//		   "INNER JOIN fetch c.pedidos p "	+	
//		   "INNER JOIN fetch p.itemPedidos ip "	+	
//		   "INNER JOIN fetch ip.produto " +
//		   "WHERE c.idComanda = :idComanda "
//	)
//	public Comanda findForPayment(long idComanda);
	
}
