package br.com.app.modelo.domain.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.com.app.modelo.domain.model.Pedido;
import lombok.Data;

@Data
public class ComandaDTO {
	
	private long idComanda;
	private BigDecimal valor;
	@NotEmpty(message = "Campo não pode ser nulo")
	private String nome;
	@NotEmpty(message = "Campo não pode ser nulo")
	private String numMesa;
	private String flgStatus;
	private List<Pedido> Pedidos;

}
