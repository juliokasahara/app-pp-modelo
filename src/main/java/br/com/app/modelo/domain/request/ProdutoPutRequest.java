package br.com.app.modelo.domain.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoPutRequest {

	private long idProduto;
	private String nome;
	private BigDecimal valor;
	private String descricao;
	
}
