package br.com.app.modelo.domain.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.modelo.domain.DTO.PagamentoDTO;
import br.com.app.modelo.domain.model.Pagamento;
import br.com.app.modelo.domain.service.PagamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("pagamentos")
public class PagamentoController {
	
	private final PagamentoService pagamentoService;
	
	@GetMapping
	public ResponseEntity<Page<Pagamento>> list(Pageable pageable) {
		return ResponseEntity.ok(pagamentoService.findAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pagamento> findById(@PathVariable long id) {
		return ResponseEntity.ok(pagamentoService.findByIdOrThrowBadRequest(id));
	}
	
//	@GetMapping(path = "/buscar")
//	public ResponseEntity<List<Pagamento>> findByNome(@RequestParam String nome) {
//		return ResponseEntity.ok(pagamentoService.findByName(nome));
//	}
	
	@PostMapping
    public ResponseEntity<Pagamento> save(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return new ResponseEntity<>(pagamentoService.save(pagamentoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
    	pagamentoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PagamentoDTO pagamentoDTO) {
    	pagamentoService.update(pagamentoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
	@RequestMapping(value = "/pagar", method = RequestMethod.POST)
    public void pay(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
		pagamentoService.pagar(pagamentoDTO);
	}

}
