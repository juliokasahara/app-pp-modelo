package br.com.app.modelo.domain.DTO;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PagamentoDTO {
	
	private Long idPagamento;
	private Date dtaPagamento;
	private BigDecimal numComanda;
	@NotNull(message = "Campo não pode ser nulo")
	private Long idComanda;
	@DecimalMin(value = "0", inclusive = false, message = "valor deve ser maior que zero")
	@Digits(integer = 100,fraction = 2,message = "aaaaaaaaaaaa")
	private BigDecimal valPago;
	private BigDecimal valComanda;

}
