package br.com.app.modelo.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data para gerar o get/set e EqualsAndHashCode
@Data
@AllArgsConstructor
@Entity(name = "Produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private BigDecimal valor;
	@Column
	private String descricao;

}
