package br.com.app.modelo.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.modelo.domain.DTO.UsuarioDTO;
import br.com.app.modelo.domain.model.Usuario;
import br.com.app.modelo.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> list(Pageable pageable) {
		return ResponseEntity.ok(usuarioService.findAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable long id) {
		return ResponseEntity.ok(usuarioService.findByIdOrThrowBadRequest(id));
	}
	
	@GetMapping(path = "/buscar")
	public ResponseEntity<List<Usuario>> findByNome(@RequestParam String nome) {
		return ResponseEntity.ok(usuarioService.findByName(nome));
	}
	
	@PostMapping
    public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.save(usuarioDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
    	usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UsuarioDTO usuarioDTO) {
    	usuarioService.update(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
