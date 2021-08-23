package br.com.app.modelo.domain.DAO;

import java.util.HashMap;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import br.com.app.modelo.comumpersistencia.comum.ResultadoPaginado;
import br.com.app.modelo.domain.model.Comanda;


public class TesteDAO extends BaseDAO{

	public ResultadoPaginado<Comanda> consultaPaginadaPicking() {
		StringBuilder sql = getConsultaPicking();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
//		if (!StringUtils.isEmpty(filtro.getFiltroGeral())) {
//			sql.append(" AND pik.SI_NUMBER LIKE :filtroGeral ");
//			parametros.put("filtroGeral", "%" + filtro.getFiltroGeral() + "%");
//		}
//		
//		if (!filtro.getExibirComErros()) {
//			sql.append(" AND erp.STR_AUTO_KEY IS NULL ");
//		}
//		
//		if (!StringUtils.isEmpty(filtro.getSequence())) {
//			sql.append(" AND pik.SEQUENCE = :sequence ");
//			parametros.put("sequence", filtro.getSequence());
//		}
		
		String[] colunas = getColunasPicking();
		
		return executarConsultaPaginadaSQL(sql.toString(), Comanda.class, parametros, 0, 10, colunas);
	}
	
	private StringBuilder getConsultaPicking() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("FROM TABLE(comanda) c");
		sql.append("  c.id_comanda \"idComanda\",");
		sql.append("  c.ind_status \"indStatus\",");
		sql.append("  c.nome \"nome\",");
		sql.append("  c.num_mesa \"numMesa\",");

		return sql;
	}
	
	private String[] getColunasPicking() {
		String[] colunas = new String[] {"id_comanda", "ind_status", "nome", "num_mesa"};
		return colunas;
	}

	@Override
	public Session getHibernateSession() {
		Session session = (Session) getEntityManager(Boolean.TRUE).getDelegate();
		session.setFlushMode(FlushMode.COMMIT);
		session.enableFilter("active_employee");
		return session;
	}

}
