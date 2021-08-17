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

import br.com.app.modelo.domain.DTO.ComandaDTO;
import br.com.app.modelo.domain.model.Comanda;
import br.com.app.modelo.domain.service.ComandaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comandas")
public class ComandaController {
	
	private final ComandaService comandaService;
	
	@GetMapping
	public ResponseEntity<Page<Comanda>> list(Pageable pageable) {
		return ResponseEntity.ok(comandaService.findAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Comanda> findById(@PathVariable long id) {
		return ResponseEntity.ok(comandaService.findByIdOrThrowBadRequest(id));
	}
	
	@GetMapping(path = "/buscar")
	public ResponseEntity<List<Comanda>> findByNome(@RequestParam String nome) {
		return ResponseEntity.ok(comandaService.findByName(nome));
	}
	
	@PostMapping
    public ResponseEntity<Comanda> save(@RequestBody @Valid ComandaDTO comandaDTO) {
        return new ResponseEntity<>(comandaService.save(comandaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
    	comandaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ComandaDTO comandaDTO) {
    	comandaService.update(comandaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
