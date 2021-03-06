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

import br.com.app.modelo.domain.DTO.ProdutoDTO;
import br.com.app.modelo.domain.model.Produto;
import br.com.app.modelo.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<Page<Produto>> list(Pageable pageable) {
		return ResponseEntity.ok(produtoService.findAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		return ResponseEntity.ok(produtoService.findByIdOrThrowBadRequest(id));
	}
	
	@GetMapping(path = "/buscar")
	public ResponseEntity<List<Produto>> findByNome(@RequestParam String nome) {
		return ResponseEntity.ok(produtoService.findByName(nome));
	}
	
	@PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return new ResponseEntity<>(produtoService.save(produtoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
    	produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ProdutoDTO produtoDTO) {
    	produtoService.update(produtoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
