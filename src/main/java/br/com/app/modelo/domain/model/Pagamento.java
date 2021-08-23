package br.com.app.modelo.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Pagamento {
	
	@Id
	@Column(name="id_pagamento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPagamento;
	@Column
	private Date dtaPagamento;
	@Column
	private BigDecimal valPago;
	@Column
	private BigDecimal valComanda;
	
	@OneToOne
	@JsonManagedReference
	@JoinColumn(name = "id_comanda",referencedColumnName = "id_comanda")
	private Comanda comanda;
	

}
