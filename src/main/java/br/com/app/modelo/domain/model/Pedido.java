package br.com.app.modelo.domain.model;

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
import javax.persistence.ManyToOne;
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
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long idPedido;
	@Column(nullable = false)
	private Integer quantidade;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comanda")
	private Comanda comanda;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "pedido_produto",	
	    joinColumns = {@JoinColumn(name = "id_pedido")},
	    inverseJoinColumns = {@JoinColumn(name = "id_produto")},
	    uniqueConstraints = {@UniqueConstraint(
	    columnNames = {"id_pedido", "id_produto"})}
	)
	private List<Produto> produtos;

}
