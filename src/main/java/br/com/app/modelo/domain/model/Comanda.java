package br.com.app.modelo.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String numMesa;
	@Column(nullable = false)
	private String indStatus;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "comanda")
	private List<Pedido> pedidos;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "comanda")
	private Pagamento pagamento;
}
