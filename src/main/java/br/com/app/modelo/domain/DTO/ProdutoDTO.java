package br.com.app.modelo.domain.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.app.modelo.domain.model.Pedido;
import lombok.Data;

@Data
public class ProdutoDTO {
	
	private long idProduto;
	@NotEmpty(message = "Campo não pode ser nulo")
	private String nome;
	@DecimalMin(value = "0", inclusive = false, message = "valor deve ser maior que zero")
	@Digits(integer = 2,fraction = 2,message = "aaaaaaaaaaaa")
	private BigDecimal valor;
	private String descricao;
	@NotNull(message = "Campo não pode ser nulo")
	private Integer estoque;
	private List<Pedido> pedidos;

}
