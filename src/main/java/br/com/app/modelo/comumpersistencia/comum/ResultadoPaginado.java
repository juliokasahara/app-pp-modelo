package br.com.app.modelo.comumpersistencia.comum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultadoPaginado<T> {
	private Long total;
	private List<T> lista;
	private Map<String, Object> dadosExtras;

	public ResultadoPaginado(Long total, List<T> lista) {
		this.total = total;
		this.lista = lista;
	}

	public Long getTotal() {
		if (this.total == null) {
			this.total = 0L;
		}
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Map<String, Object> getDadosExtras() {
		if (this.dadosExtras == null) {
			this.dadosExtras = new HashMap<String, Object>();
		}
		return this.dadosExtras;
	}

	public void setDadosExtras(Map<String, Object> dadosExtras) {
		this.dadosExtras = dadosExtras;
	}

}
