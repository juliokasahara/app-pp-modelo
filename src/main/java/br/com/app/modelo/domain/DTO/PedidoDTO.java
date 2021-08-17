package br.com.app.modelo.domain.DTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.com.app.modelo.domain.model.Comanda;
import br.com.app.modelo.domain.model.Produto;
import lombok.Data;

@Data
public class PedidoDTO {
	
	private Long idPedido;
	@NotEmpty(message = "Campo não pode ser nulo")
	private Integer quantidade;
	@NotEmpty(message = "Campo não pode ser nulo")
	private Comanda comanda;
	@NotEmpty(message = "Campo não pode ser nulo")
	private List<Produto> produtos;

}
