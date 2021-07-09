package br.com.app.modelo.domain.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoPutDTO {

	private long idProduto;
	private String nome;
	private BigDecimal valor;
	private String descricao;
	
}
