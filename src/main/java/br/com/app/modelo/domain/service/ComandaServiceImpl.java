package br.com.app.modelo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.modelo.comumpersistencia.comum.ResultadoPaginado;
import br.com.app.modelo.domain.DAO.ComandaDAO;
import br.com.app.modelo.domain.DAO.PedidoDAO;
import br.com.app.modelo.domain.DAO.TesteDAO;
import br.com.app.modelo.domain.DTO.ComandaDTO;
import br.com.app.modelo.domain.exception.BadRequestException;
import br.com.app.modelo.domain.mapper.ComandaMapper;
import br.com.app.modelo.domain.model.Comanda;
import br.com.app.modelo.domain.model.Pedido;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(noRollbackFor=Exception.class, value="comadaMRO")
public class ComandaServiceImpl implements ComandaService{
	
	private final ComandaDAO comandaDAO;
	private final PedidoDAO pedidoDAO;
	private final TesteDAO testeDAO; 
// @formatter:on


	public Page<Comanda> findAll(Pageable pageable) {
		return comandaDAO.findAll(pageable);
	}
	
	public List<Comanda> findByName(String nome) {
		return comandaDAO.findByNome(nome);
	}

	public Comanda findByIdOrThrowBadRequest(long id) {
//		return comandaDAO.findById(id).orElseThrow(() -> new BadRequestException("Comanda não existe"));
//		return comandaDAO.findForPayment(id);
		ResultadoPaginado<Comanda> a = testeDAO.consultaPaginadaPicking();
		return null;
	}

	public void delete(long id) {
		comandaDAO.delete(findByIdOrThrowBadRequest(id));
	}
	
//	checked unchecked exception
	@Transactional(rollbackFor = Exception.class)
	public Comanda save(ComandaDTO comandaDTO) {
		Comanda comanda = ComandaMapper.INSTANCE.toComanda(comandaDTO);
		
		comanda.setIndStatus("A");
		
		Comanda comandaDataBase = comandaDAO.save(comanda);
		
//		List<Pedido> pedidos = comanda.getPedidos();
//		
//		for (Pedido pedido : pedidos) {
//			pedido.setComanda(comandaDataBase);
//		}
//		
//		pedidoDAO.saveAll(pedidos);
		
		return comandaDataBase;
	}

	public void update(ComandaDTO comandaDTO) {
		Comanda comandaBanco = findByIdOrThrowBadRequest(comandaDTO.getIdComanda());
		Comanda comanda = ComandaMapper.INSTANCE.toComanda(comandaDTO);
		comanda.setIdComanda(comandaBanco.getIdComanda());
		comandaDAO.save(comanda);
	}

}
