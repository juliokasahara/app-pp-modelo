package br.com.app.modelo.domain.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdutoPostDTO {
	
	@NotEmpty(message = "Campo não pode ser nulo")
	private String nome;
	@DecimalMin(value = "0.0", inclusive = false, message = "valor deve ser maior que zero")
	@Digits(integer=10, fraction=2, message = "Padrao incorreto")
	private BigDecimal valor;
	private String descricao;
	@NotNull(message = "Campo não pode ser nulo")
	private Integer quantidade;

}
