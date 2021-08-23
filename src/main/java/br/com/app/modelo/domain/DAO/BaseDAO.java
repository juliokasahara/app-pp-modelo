package br.com.app.modelo.domain.DAO;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;

import br.com.app.modelo.comumpersistencia.comum.ResultadoPaginado;

public abstract class BaseDAO {

	protected static Map<Class, AbstractSingleColumnStandardBasicType> tipos = new HashMap<Class, AbstractSingleColumnStandardBasicType>();
	
	@PersistenceContext(unitName = "comadaMRO")
	private EntityManager entityManagerMro;
	
	static {
		tipos.put(String.class, new StringType());
		tipos.put(Integer.class, new IntegerType());
		tipos.put(Date.class, new TimestampType());
		tipos.put(Long.class, new LongType());
		tipos.put(BigDecimal.class, new BigDecimalType());
		tipos.put(Boolean.class, new BooleanType());
	}
 
 

	public <T> ResultadoPaginado<T> executarConsultaPaginada(String sql, Map<String, Object> parametros, Integer inicio, Integer quantidadePorPagina) {
		Integer posFrom = sql.toLowerCase().indexOf("from ");
		StringBuilder hqlCount = new StringBuilder(sql).replace(0, posFrom, "SELECT COUNT(*) ");
		
		return executarConsultaPaginada(sql, hqlCount.toString(), parametros, inicio, quantidadePorPagina);
	}
	
	public <T> ResultadoPaginado<T> executarConsultaPaginada(String sql, String sqlCount, Map<String, Object> parametros, Integer inicio, Integer quantidadePorPagina) {

		Query queryCount = getHibernateSession().createQuery(sqlCount);
		atribuirParametrosAConsulta(queryCount, parametros);
		Long totalRegistros = (Long) queryCount.uniqueResult();
		
		StringBuilder hql = new StringBuilder(sql);
		Query query = getHibernateSession().createQuery(hql.toString());
		query.setMaxResults(quantidadePorPagina);
		query.setFirstResult(inicio);

		atribuirParametrosAConsulta(query, parametros);
		
		List<T> lista = query.list();
		
		ResultadoPaginado<T> resultadoPaginado = new ResultadoPaginado<T>(totalRegistros, lista);
		
		return resultadoPaginado;
	}

	
	protected void atribuirParametrosAConsulta(Query query, Map<String, Object> parametros) {
		for (Entry<String, Object> entry : parametros.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				query.setParameterList(entry.getKey(), (Collection) entry.getValue());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public <T> ResultadoPaginado<T> executarConsultaPaginadaSQL(String sql, Class transformer, Map<String, Object> parametros, Integer inicio, Integer quantidadePorPagina, String... colunas) {
		Integer posFrom = sql.toLowerCase().indexOf("from");
		StringBuilder hqlCount = new StringBuilder(sql).replace(0, posFrom, "SELECT COUNT(*) ");
		
		return executarConsultaPaginadaSQL(sql, hqlCount.toString(), transformer, parametros, inicio, quantidadePorPagina, colunas);
	}
	
	@SuppressWarnings("rawtypes")
	public <T> ResultadoPaginado<T> executarConsultaPaginadaSQL(String sql, String sqlCount, Class transformer, Map<String, Object> parametros, Integer inicio, Integer quantidadePorPagina, String... colunas) {
		SQLQuery queryCount = getHibernateSession().createSQLQuery(sqlCount);
		atribuirParametrosAConsulta(queryCount, parametros);
		Long totalRegistros = (Long) ((BigDecimal)queryCount.uniqueResult()).longValue();
		
		StringBuilder hql = new StringBuilder(sql);
		SQLQuery query = getHibernateSession().createSQLQuery(hql.toString());
		
		addScalar(transformer, query, colunas);
		
		query.setResultTransformer(Transformers.aliasToBean(transformer));
		query.setMaxResults(quantidadePorPagina);
		query.setFirstResult(inicio);

		atribuirParametrosAConsulta(query, parametros);
		
		List<T> lista = query.list();
		
		ResultadoPaginado<T> resultadoPaginado = new ResultadoPaginado<T>(totalRegistros, lista);
		
		return resultadoPaginado;
	}

 
	protected void addScalar(Class transformer, SQLQuery query, String... colunas) {
		if (colunas != null) {
			for (String coluna : colunas) {
				try {
					Field field = transformer.getDeclaredField(coluna);
					Class type = field.getType();
					query.addScalar(coluna, tipos.get(type));
				} catch (NoSuchFieldException e) {
					//
				} catch (SecurityException e) {
					//
				}
			}
		}
	}
	
	protected abstract Session getHibernateSession();
	
	protected void addScalar(SQLQuery query, Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {;
			Class type = field.getType();
			query.addScalar(field.getName(), tipos.get(type));
		}
	}
	
	protected String[] getColunasScalar(Class clazz) {
		List<String> retorno = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {;
			Class type = field.getType();
			retorno.add(field.getName());
		}
		
		return retorno.toArray(new String[retorno.size()]);
	}
	
	public EntityManager getEntityManager(Boolean caseIncensitive) {
		if (caseIncensitive) {
			entityManagerMro.createNativeQuery("alter session set NLS_COMP=LINGUISTIC").executeUpdate();
			entityManagerMro.createNativeQuery("alter session set NLS_SORT=BINARY_AI").executeUpdate();
		}
		return entityManagerMro;
	}
}
