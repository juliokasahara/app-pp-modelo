package br.com.app.modelo.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data para gerar o get/set e EqualsAndHashCode

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comanda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comanda")
	private Long idComanda;
	@Column
	private BigDecimal valor;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String numMesa;
	@Column(nullable = false)
	private String flgStatus;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "comanda")
	private List<Pedido> pedidos;


}
