package br.com.app.modelo.domain.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private long idUsuario;
	@NotEmpty(message = "Campo não pode ser nulo")
	private String nome;
	@NotEmpty(message = "Campo não pode ser nulo")
	private String senha;

}
