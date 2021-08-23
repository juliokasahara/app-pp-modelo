package br.com.app.modelo.domain.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.modelo.domain.DAO.ComandaDAO;
import br.com.app.modelo.domain.DAO.PagamentoDAO;
import br.com.app.modelo.domain.DTO.PagamentoDTO;
import br.com.app.modelo.domain.exception.BadRequestException;
import br.com.app.modelo.domain.mapper.PagamentoMapper;
import br.com.app.modelo.domain.model.Comanda;
import br.com.app.modelo.domain.model.ItemPedido;
import br.com.app.modelo.domain.model.Pagamento;
import br.com.app.modelo.domain.model.Pedido;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService{
	
	private final PagamentoDAO pagamentoDAO;
	private final ComandaDAO comandaDAO;

	public Page<Pagamento> findAll(Pageable pageable) {
		return pagamentoDAO.findAll(pageable);
	}

	public Pagamento findByIdOrThrowBadRequest(long id) {
		return pagamentoDAO.findById(id).orElseThrow(() -> new BadRequestException("Pagamento não existe"));
	}

	public void delete(long id) {
		pagamentoDAO.delete(findByIdOrThrowBadRequest(id));
	}
	
//	checked unchecked exception
	@Transactional(rollbackFor = Exception.class)
	public Pagamento save(PagamentoDTO pagamentoDTO) {
		return pagamentoDAO.save(PagamentoMapper.INSTANCE.toPagamento(pagamentoDTO));
	}

	public void update(PagamentoDTO pagamentoDTO) {
		Pagamento pagamentoBanco = findByIdOrThrowBadRequest(pagamentoDTO.getIdPagamento());
		Pagamento pagamento = PagamentoMapper.INSTANCE.toPagamento(pagamentoDTO);
		pagamento.setIdPagamento(pagamentoBanco.getIdPagamento());
		pagamentoDAO.save(pagamento);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pagar(@Valid PagamentoDTO pagamentoDTO) {
		
		try {
			
			Comanda comanda = comandaDAO.findById(pagamentoDTO.getIdComanda()).orElseThrow(() -> new BadRequestException("Comanda não existe"));
			Pagamento pagamento = new Pagamento();
			pagamento.setComanda(comanda);
			pagamento.setDtaPagamento(new Date());
			pagamento.setValPago(pagamento.getValPago());
			
			BigDecimal valComanda = new BigDecimal(0);
			
			for (Pedido pedido : comanda.getPedidos()) {
				for (ItemPedido ip : pedido.getItemPedidos()) {
					valComanda = valComanda.add(ip.getProduto().getValProduto());
				}
			}
			
			pagamento.setValComanda(valComanda);
			pagamentoDAO.save(pagamento);
			
		}catch (Exception e) {
			throw e;
		}
		
	}

}
